package com.example.todo

import com.example.todo.database.ListItemEntity
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.view.ItemParameters
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import java.time.LocalDate

class EditItemInteractorTest {

    @Test
    fun getItemTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val expectItemModel = ListItemModel(42, "title", "desc", LocalDate.now(), false)

        every {
            repository.get(42)
        } returns Single.just(expectItemModel.copyToEntity())

        val interactor = EditItemInteractor(ItemParameters(42), repository)

        val observer = TestObserver.create<ListItemModel>()
        interactor.getItem().subscribe(observer)

        observer.assertComplete()
        observer.assertValue(expectItemModel)
        verifyAll {
            repository.get(42)
        }
    }

    @Test
    fun editItemTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val expectItemModel = ListItemModel(42, "title", "desc", LocalDate.now(), false)
        val oldItemEntity = ListItemEntity(42, "old title", "old desc", LocalDate.now(), false)

        every {
            repository.get(42)
        } returns Single.just(oldItemEntity)

        every {
            repository.update(any())
        } returns Completable.complete()

        val interactor = EditItemInteractor(ItemParameters(42), repository)

        val observer = TestObserver.create<Completable>()
        interactor.editItem(expectItemModel.title, expectItemModel.description, expectItemModel.date)
            .subscribe(observer)

        observer.assertComplete()
        verifyAll {
            repository.get(42)
            repository.update(expectItemModel)
        }
    }
}