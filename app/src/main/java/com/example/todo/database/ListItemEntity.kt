package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate

@Entity(tableName = "item_table")
data class ListItemEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val date: LocalDate,
    val done: Boolean
)