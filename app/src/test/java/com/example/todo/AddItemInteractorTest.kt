package com.example.todo

import com.example.todo.interactors.AddItemInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class AddItemInteractorTest {
    @Test
    fun addItemTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val expectItemModel = ListItemModel(0, "title", "desc", LocalDate.now(), false)

        every {
            repository.add(expectItemModel)
        } returns Single.just(42)

        val interactor = AddItemInteractor(repository)

        val observer = TestObserver.create<Long>()
        interactor.addItem(
            expectItemModel.title,
            expectItemModel.description,
            expectItemModel.date
        ).subscribe(observer)

        observer.assertComplete()
        observer.assertValue(42)

        verifyAll {
            repository.add(expectItemModel)
        }
    }
}
