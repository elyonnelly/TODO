package com.example.todo

class TodoRepository(private val todoList : MutableList<ListItemModel> = mutableListOf()
) : Repository<ListItemModel> {

    override val size: Int
        get() {
            return todoList.size
        }

    override fun get(i: Int): ListItemModel {
        return todoList[i]
    }

    override fun update(value : ListItemModel) {
        todoList.add(value)
    }

    override fun remove(i: Int) {
        todoList.removeAt(i)
    }
}