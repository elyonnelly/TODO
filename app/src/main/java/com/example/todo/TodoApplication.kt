package com.example.todo

import android.app.Application
import com.example.todo.components.AppComponent
import com.example.todo.components.DaggerAppComponent

class TodoApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = buildAppComponent()
        //repository = TodoDbRepository(TodoListRoomDatabase.getDatabase(context = this).todoListDao())//SimpleTodoRepository()
    }

    protected fun buildAppComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .context(this)
            .build()
    }

}