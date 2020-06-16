package com.example.todo

import androidx.test.runner.AndroidJUnit4
import com.example.todo.interactors.ListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ListInteractorTest : InteractorTest() {
    @Test
    fun listInteractorTest() {
        val listInteractor = ListInteractor(appComponent.repository())
        addItems(10)

    }

    private fun addItems(n : Int) {
        val newItem = ListItemModel(0,"title", "description", LocalDate.now(), false)
        val listIds = mutableListOf<Long>()
        for(i in 1..n) {
            appComponent.repository().add(newItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                        it -> listIds.add(it)
                }
        }
    }


}