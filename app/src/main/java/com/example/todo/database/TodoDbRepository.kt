package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.copyToEntity
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class TodoDbRepository @Inject constructor(private val todoDao : TodoDao, private val scheduler: Scheduler) : Repository<ListItemModel> {

    override fun get(id: Long): Single<ListItemEntity> {
        return todoDao.getById(id)
                .subscribeOn(scheduler)
    }

    override fun add(value: ListItemModel) : Single<Long> {
        return todoDao.add(value.copyToEntity()).subscribeOn(scheduler)
    }

    override fun update(value: ListItemModel) : Completable {
        return todoDao.update(value.copyToEntity()).subscribeOn(scheduler)
    }

    override fun remove(id: Long) : Completable {
        return todoDao.remove(id).subscribeOn(scheduler)
    }

    override fun getAll(): Single<List<ListItemEntity>> {
        return todoDao.getAll().subscribeOn(scheduler)
    }

    override fun clear(): Completable {
        return todoDao.clear().subscribeOn(scheduler)
    }
}

