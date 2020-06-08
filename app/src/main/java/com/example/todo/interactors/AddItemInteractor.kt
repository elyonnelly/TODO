package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository
import java.time.LocalDate

class AddItemInteractor(private val repository : Repository<ListItemModel>) {

    fun addItem(title : String, description : String, date : LocalDate) {
        repository.add(ListItemModel(0, title, description, date, false))
    }
}