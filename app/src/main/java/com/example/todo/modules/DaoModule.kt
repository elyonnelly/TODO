package com.example.todo.modules

import com.example.todo.database.TodoDao
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.DatabaseScope
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    @DatabaseScope
    fun provideDao(database : TodoListRoomDatabase) : TodoDao {
        return database.todoListDao()
    }
}