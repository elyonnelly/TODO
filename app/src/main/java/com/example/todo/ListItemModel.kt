package com.example.todo

import com.example.todo.database.ListItemEntity
import java.time.LocalDate

data class ListItemModel(
    val id: Long,
    val title: String,
    val description: String,
    val date: LocalDate,
    val done: Boolean)

fun ListItemModel.copyToEntity(): ListItemEntity {
    return ListItemEntity(id,
        title,
        description,
        date,
        done)
}