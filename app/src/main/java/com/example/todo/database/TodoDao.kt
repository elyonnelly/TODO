package com.example.todo.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface TodoDao {
    @Query("SELECT COUNT(*) FROM item_table")
    fun getSize() : Single<Int>
    @Query("SELECT * FROM item_table WHERE ID = :id")
    fun getById(id : Long) : Single<ListItemEntity>
    @Insert
    fun add(value : ListItemEntity) : Single<Long>
    @Query("DELETE FROM item_table WHERE ID = :id")
    fun remove(id : Long) : Completable
    @Query("SELECT * FROM item_table")
    fun getAll() : Single<List<ListItemEntity>>
    @Update
    fun update(value : ListItemEntity) : Completable
    @Query("DELETE FROM item_table")
    fun clear() : Completable
}