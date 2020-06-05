package com.example.todo

import java.time.LocalDate

class TodoRepository : Repository<ListItemModel> {

    private val todoList : MutableMap<Int, ListItemModel> = mutableMapOf()
    private var currentId : Int = 0;

    override val size: Int
        get() {
            return todoList.size
        }

    override fun get(id: Int): ListItemModel {
        return todoList[id]!!
    }

    override fun add(value : ListItemModel) {
        value.id = currentId++
        todoList[value.id!!] = value
    }

    override fun remove(id: Int) {
        todoList.remove(id)
    }

    override fun getAllItems(): MutableList<ListItemModel> {
        return todoList.values.toMutableList()
    }

    override fun update(value : ListItemModel) {
        todoList[value.id!!] = value
    }
}