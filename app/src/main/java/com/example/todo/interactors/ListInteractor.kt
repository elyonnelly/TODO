package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository

class ListInteractor(private val repository : Repository<ListItemModel>) {

    fun getAll() : List<ListItemModel> {
        return repository.getAllItems()
    }

    fun getActive() : List<ListItemModel> {
        val items = repository.getAllItems()
        val active = mutableListOf<ListItemModel>()
        for (item in items) {
            if (!item.done) {
                active.add(item)
            }
        }
        return active
    }

    fun getDone() : List<ListItemModel> {
        val items = repository.getAllItems()
        val done = mutableListOf<ListItemModel>()
        for (task in items) {
            if (task.done) {
                done.add(task)
            }
        }
        return done
    }

    fun changeTaskStatus(id : Int, status : Boolean) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(done = status))
    }
}