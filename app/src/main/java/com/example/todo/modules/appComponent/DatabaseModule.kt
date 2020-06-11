package com.example.todo.modules.appComponent

import android.content.Context
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    @AppScope
    fun provideTodoListRoomDatabase(context : Context) : TodoListRoomDatabase {
        return TodoListRoomDatabase.getDatabase(context)
    }
}