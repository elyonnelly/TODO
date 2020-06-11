package com.example.todo.modules.appComponent

import com.example.todo.database.TodoDao
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    @AppScope
    fun provideDao(database : TodoListRoomDatabase) : TodoDao {
        return database.todoListDao()
    }
}