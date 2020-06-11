package com.example.todo.modules

import android.content.Context
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.TodoDao
import com.example.todo.database.TodoDbRepository
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideTodoListRoomDatabase(context : Context) : TodoListRoomDatabase {
        return TodoListRoomDatabase.getDatabase(context)
    }

    @Provides
    @AppScope
    fun provideDao(database : TodoListRoomDatabase) : TodoDao {
        return database.todoListDao()
    }

    @Provides
    @AppScope
    fun provideRepository(todoDao: TodoDao) : Repository<ListItemModel> {
        return TodoDbRepository(todoDao)
    }
}