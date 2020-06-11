package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.copyToEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TodoDbRepository @Inject constructor(private val todoDao : TodoDao) : Repository<ListItemModel> {
    var currentId : Int = 0

    init {
        todoDao.getSize().subscribeOn(Schedulers.io()).subscribe {
            size -> currentId = size
        }
    }

    override fun get(id: Int): Single<ListItemEntity> {
        return todoDao.getById(id)
                .subscribeOn(Schedulers.io())
    }

    override fun add(value: ListItemModel) : Completable {
        return todoDao.add(value.copy(id = currentId++).copyToEntity()).subscribeOn(Schedulers.io())
    }

    override fun update(value: ListItemModel) : Completable {
        return todoDao.update(value.copyToEntity()).subscribeOn(Schedulers.io())
    }

    override fun remove(id: Int) : Completable {
        return todoDao.remove(id).subscribeOn(Schedulers.io())
    }

    override fun getAll(): Single<List<ListItemEntity>> {
        return todoDao.getAll().subscribeOn(Schedulers.io())
    }
}

