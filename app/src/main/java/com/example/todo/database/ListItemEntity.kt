package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "item_table")
data class ListItemEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
   // @ColumnInfo val date: LocalDate,
    @ColumnInfo val done: Boolean
)