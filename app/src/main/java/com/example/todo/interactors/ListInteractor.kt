package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository

class ListInteractor(private val repository : Repository<ListItemModel>) {

    fun getAll() : List<ListItemModel> {
        return repository.getAll()
    }

    fun getActive() : List<ListItemModel> {
        val items = repository.getAll()
        return items.filter{!it.done}.toList()
    }

    fun getDone() : List<ListItemModel> {
        val items = repository.getAll()
        return items.filter{it.done}.toList()
    }

    fun changeTaskStatus(id : Int, status : Boolean) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(done = status))
    }

    fun checkTaskStatus(id : Int) : Boolean {
        return repository.get(id).done
    }
}