package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository
import java.time.LocalDate

class EditItemInteractor(private val id : Int, private val repository : Repository<ListItemModel>) {

    fun getItem() : ListItemModel {
        return repository.get(id)
    }
    fun editItem(newTitle : String, newDescription : String, newDate : LocalDate) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(title = newTitle, description = newDescription, date = newDate))
    }
}