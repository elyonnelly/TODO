package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.copyToEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import java.time.LocalDate
import kotlin.random.Random

class TodoDbRepositoryTest {

    @Test
    fun get() {
        val todoDao = mockk<TodoDao>()
        val id : Long = 86
        val expectItemEntity = ListItemEntity(id, "title", "desc", LocalDate.now(), false)

        every {
            todoDao.getById(id)
        } returns Single.just(expectItemEntity)

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<ListItemEntity>()
        repository.get(id).subscribe(observer)

        observer.assertComplete()
        observer.assertValue(expectItemEntity)

        verifyAll {
            todoDao.getById(id)
        }
    }

    @Test
    fun add() {
        val todoDao = mockk<TodoDao>()
        val id : Long = 86
        val expectItemModel = ListItemModel(0, "title", "desc", LocalDate.now(), false)

        every {
            todoDao.add(expectItemModel.copyToEntity())
        } returns Single.just(id)

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<Long>()
        repository.add(expectItemModel).subscribe(observer)

        observer.assertComplete()
        observer.assertValue(id)

        verifyAll {
            todoDao.add(expectItemModel.copyToEntity())
        }
    }

    @Test
    fun update() {
        val todoDao = mockk<TodoDao>()
        val id : Long = 86
        val expectItemModel = ListItemModel(id, "title", "desc", LocalDate.now(), false)

        every {
            todoDao.update(expectItemModel.copyToEntity())
        } returns Completable.complete()

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<Completable>()
        repository.update(expectItemModel).subscribe(observer)

        observer.assertComplete()

        verifyAll {
            todoDao.update(expectItemModel.copyToEntity())
        }
    }

    @Test
    fun remove() {
        val todoDao = mockk<TodoDao>()
        val id : Long = 86

        every {
            todoDao.remove(id)
        } returns Completable.complete()

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<Completable>()
        repository.remove(id).subscribe(observer)

        observer.assertComplete()

        verifyAll {
            todoDao.remove(id)
        }
    }

    @Test
    fun getAll() {
        val expectedItems = List(10) {
                i -> ListItemEntity(i.toLong(), "title", "desc", LocalDate.now(), Random.nextBoolean())
        }
        val todoDao = mockk<TodoDao>()

        every {
            todoDao.getAll()
        } returns Single.just(expectedItems)

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<List<ListItemEntity>>()
        repository.getAll().subscribe(observer)


        observer.assertComplete()
        observer.assertValue { it == expectedItems }

        verifyAll {
            todoDao.getAll()
        }
    }

    @Test
    fun clear() {
        val todoDao = mockk<TodoDao>()

        every {
            todoDao.clear()
        } returns Completable.complete()

        val repository = TodoDbRepository(todoDao, Schedulers.trampoline())

        val observer = TestObserver.create<Completable>()
        repository.clear().subscribe(observer)

        observer.assertComplete()

        verifyAll {
            todoDao.clear()
        }
    }
}