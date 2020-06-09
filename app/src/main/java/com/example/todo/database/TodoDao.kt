package com.example.todo.database

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT COUNT(*) FROM item_table")
    fun getSize() : Int
    @Query("SELECT * FROM item_table WHERE ID = :id")
    fun getById(id : Int) : ListItemEntity
    @Insert
    fun add(value : ListItemEntity)
    @Query("DELETE FROM item_table WHERE ID = :id")
    fun remove(id : Int)
    @Query("SELECT * FROM item_table")
    fun getAll() : List<ListItemEntity>
    @Update
    fun update(value : ListItemEntity)
}