package com.example.todo

import androidx.test.runner.AndroidJUnit4
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.view.ItemParameters
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EditItemInteractorTest : InteractorTest() {
    @Test
    fun editItemInteractorTest() {
        val newItem = ListItemModel(0,"title", "description", LocalDate.now(), false)
        var editId : Long = 0
        appComponent.repository().add(newItem).subscribe {
            id -> editId = id
        }
        val editItemInteractor = EditItemInteractor(ItemParameters(editId), appComponent.repository())
        //плохо, наверное
        var addedItem = ListItemModel(0, "", "", LocalDate.now(), true)
        editItemInteractor.getItem()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it -> addedItem = it }

        Assert.assertEquals(newItem.title, addedItem.title)
        Assert.assertEquals(newItem.description, addedItem.description)
        Assert.assertEquals(newItem.date, addedItem.date)
        Assert.assertEquals(newItem.done, addedItem.done)

        val newFields = addedItem.copy(title = "new title", description = "new description")
        editItemInteractor.editItem(newFields.title, newFields.description, newFields.date)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        var editedItem = ListItemModel(0, "", "", LocalDate.now(), true)
        editItemInteractor.getItem()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it -> editedItem = it }

        Assert.assertEquals(newFields, editedItem)
    }
}