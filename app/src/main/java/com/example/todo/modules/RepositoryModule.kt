package com.example.todo.modules

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.TodoDao
import com.example.todo.database.TodoDbRepository
import com.example.todo.scopes.DatabaseScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    @DatabaseScope
    fun provideRepository(todoDao : TodoDao) : Repository<ListItemModel> {
        return TodoDbRepository(todoDao)
    }
}