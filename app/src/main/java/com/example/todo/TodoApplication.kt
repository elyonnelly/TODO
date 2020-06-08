package com.example.todo

import android.app.Application

class TodoApplication : Application() {
    lateinit var repository : Repository<ListItemModel>
        private set

    override fun onCreate() {
        super.onCreate()
         repository = SimpleTodoRepository()
    }
}