package com.example.todo

import java.time.LocalDate

/*class SimpleTodoRepository : Repository<ListItemModel> {

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
        todoList[currentId] = value.copy(id = currentId)
        currentId++
    }

    override fun remove(id: Int) {
        todoList.remove(id)
    }

    override fun getAll(): List<ListItemModel> {
        return todoList.values.toMutableList()
    }

    override fun update(value : ListItemModel) {
        todoList[value.id] = value
    }
}*/