package com.example.todo

import android.app.Application
import com.example.todo.database.TodoDbRepository
import com.example.todo.database.TodoListRoomDatabase

class TodoApplication : Application() {
    lateinit var repository : Repository<ListItemModel>
        private set

    override fun onCreate() {
        super.onCreate()
        repository = TodoDbRepository(TodoListRoomDatabase.getDatabase(context = this).todoListDao())//SimpleTodoRepository()
    }
}