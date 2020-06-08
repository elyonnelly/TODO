package com.example.todo

import java.time.LocalDate

data class ListItemModel(
    val title: String,
    val description: String,
    val date: LocalDate,
    val done: Boolean,
    val id: Int)