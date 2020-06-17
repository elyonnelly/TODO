package com.example.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.ListItemModel
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "item_table")
data class ListItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time : LocalTime,
    val done: Boolean
)

fun ListItemEntity.copyToModel(): ListItemModel {
    return ListItemModel(id,
        title,
        description,
        date,
        time,
        done)
}