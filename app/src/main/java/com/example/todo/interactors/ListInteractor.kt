package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository

class ListInteractor(private val repository : Repository<ListItemModel>) {

    fun getAll() : List<ListItemModel> {
        return repository.getAllItems()
    }

    fun getActive() : List<ListItemModel> {
        val items = repository.getAllItems()
        return items.filter{!it.done}.toList()
    }

    fun getDone() : List<ListItemModel> {
        val items = repository.getAllItems()
        return items.filter{it.done}.toList()
    }

    fun changeTaskStatus(id : Int, status : Boolean) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(done = status))
    }
}