package com.example.todo

import com.example.todo.database.ListItemEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository<T> {
    fun get(id : Int) : Single<ListItemEntity>
    fun add(value : T): Completable
    fun update(value : T): Completable
    fun remove(id : Int): Completable
    fun getAll() : Single<List<ListItemEntity>>
}