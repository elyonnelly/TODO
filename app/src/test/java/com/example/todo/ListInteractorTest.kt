package com.example.todo

import com.example.todo.database.ListItemEntity
import com.example.todo.database.copyToModel
import com.example.todo.interactors.ListInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class ListInteractorTest {
    @Test
    fun getAllTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val items = createItems()
        val models = items.map { item -> item.copyToModel() }

        every {
            repository.getAll()
        } returns Single.just(items)

        val interactor = ListInteractor(repository)
        val observer = TestObserver.create<List<ListItemModel>>()
        interactor.getAll().subscribe(observer)

        observer.assertComplete()
        observer.assertValue { it.containsAll(models) and models.containsAll(it) }
    }

    @Test
    fun getActiveTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val items = createItems()
        val models = items.map { item -> item.copyToModel() }
        val activeModels = models.filter { item -> !item.done }

        every {
            repository.getAll()
        } returns Single.just(items)

        val interactor = ListInteractor(repository)
        val observer = TestObserver.create<List<ListItemModel>>()
        interactor.getActive().subscribe(observer)

        observer.assertComplete()
        observer.assertValue { it.containsAll(activeModels) and activeModels.containsAll(it) }
    }

    @Test
    fun getDoneTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val items = createItems()
        val models = items.map { item -> item.copyToModel() }
        val completedModels = models.filter { item -> item.done }

        every {
            repository.getAll()
        } returns Single.just(items)

        val interactor = ListInteractor(repository)
        val observer = TestObserver.create<List<ListItemModel>>()
        interactor.getDone().subscribe(observer)

        observer.assertComplete()
        observer.assertValue { it.containsAll(completedModels) and completedModels.containsAll(it) }
    }

    @Test
    fun changeTaskStatusTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val oldItemEntity = ListItemEntity(42, "old title", "old desc", LocalDate.now(), false)
        val newStatus  = true

        every {
            repository.get(42)
        } returns Single.just(oldItemEntity)

        every {
            repository.update(any())
        } returns Completable.complete()

        val interactor = ListInteractor(repository)

        val observer = TestObserver.create<Completable>()
        interactor.changeTaskStatus(42, newStatus).subscribe(observer)

        observer.assertComplete()
        verifyAll {
            repository.get(42)
            repository.update(oldItemEntity.copy(done = newStatus).copyToModel())
        }
    }

    @Test
    fun checkTaskStatusTest() {
        val repository = mockk<Repository<ListItemModel>>()
        val itemEntity = ListItemEntity(42, "old title", "old desc", LocalDate.now(), false)

        every {
            repository.get(42)
        } returns Single.just(itemEntity)

        val interactor = ListInteractor(repository)

        val observer = TestObserver.create<Boolean>()
        interactor.checkTaskStatus(42).subscribe(observer)

        observer.assertComplete()
        observer.assertValue(itemEntity.done)
        verifyAll {
            repository.get(42)
        }
    }

    private fun createItems() : List<ListItemEntity> {
        val list = mutableListOf<ListItemEntity>()
        for(i in 1..10) {
            val randomModel = ListItemEntity(i.toLong(), "title", "desc", LocalDate.now(), Random.nextBoolean())
            list.add(randomModel)
        }
        return list.toList()
    }
}