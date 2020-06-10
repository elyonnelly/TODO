package com.example.todo.modules

import android.content.Context
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.DatabaseScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    @DatabaseScope
    fun provideTodoListRoomDatabase(context : Context) : TodoListRoomDatabase {
        return TodoListRoomDatabase.getDatabase(context)
    }
}