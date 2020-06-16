package com.example.todo

import androidx.test.runner.AndroidJUnit4
import com.example.todo.database.ListItemEntity
import com.example.todo.interactors.AddItemInteractor
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class AddItemInteractorTest : InteractorTest() {

    @Test
    fun addItemInteractorTest() {
        val addItemInteractor = AddItemInteractor(appComponent.repository())
        val newItem = ListItemModel(0,"title", "description", LocalDate.now(), false)

        addNewItem(newItem, addItemInteractor)
//мокаю ровно один метод и ровно с такими-то аргументами, и проверяем, что вызывается метод
        //Single.just(1) типа возвращать
        var items = emptyList<ListItemEntity>()
        appComponent.repository().getAll().observeOn(AndroidSchedulers.mainThread()).subscribe(TestSubscriber<List<ListItemEntity>>())

        Assert.assertEquals(1, items.size)

        checkContainsElement(newItem, items)

        addNewItem(newItem, addItemInteractor)
        addNewItem(newItem, addItemInteractor)

        appComponent.repository().getAll().observeOn(AndroidSchedulers.mainThread()).subscribe{ it -> items = it}

        Assert.assertEquals(3, items.size)
        checkContainsElement(newItem, items)
    }

    private fun checkContainsElement(newItem : ListItemModel, items : List<ListItemEntity>) {
        val item = items[0]
        Assert.assertEquals(newItem.title, item.title)
        Assert.assertEquals(newItem.description, item.description)
        Assert.assertEquals(newItem.date, item.date)
        Assert.assertFalse(item.done)
    }

    private fun addNewItem(item : ListItemModel, addItemInteractor: AddItemInteractor) {
        addItemInteractor.addItem(item.title, item.description, item.date)
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}