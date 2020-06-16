package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.copyToEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TodoDbRepository @Inject constructor(private val todoDao : TodoDao) : Repository<ListItemModel> {

    override fun get(id: Long): Single<ListItemEntity> {
        return todoDao.getById(id)
                .subscribeOn(Schedulers.io())
    }

    override fun add(value: ListItemModel) : Single<Long> {
        return todoDao.add(value.copyToEntity()).subscribeOn(Schedulers.io())
    }

    override fun update(value: ListItemModel) : Completable {
        return todoDao.update(value.copyToEntity()).subscribeOn(Schedulers.io())
    }

    override fun remove(id: Long) : Completable {
        return todoDao.remove(id).subscribeOn(Schedulers.io())
    }

    override fun getAll(): Single<List<ListItemEntity>> {
        return todoDao.getAll().subscribeOn(Schedulers.io())
    }

    override fun clear(): Completable {
        return todoDao.clear().subscribeOn(Schedulers.io())
    }
}

