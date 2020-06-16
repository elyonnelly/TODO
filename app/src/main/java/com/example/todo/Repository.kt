package com.example.todo

import com.example.todo.database.ListItemEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository<T> {
    fun get(id : Long) : Single<ListItemEntity>
    fun add(value : T): Single<Long>
    fun update(value : T): Completable
    fun remove(id : Long): Completable
    fun getAll() : Single<List<ListItemEntity>>
    fun clear() : Completable
}