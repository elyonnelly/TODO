package com.example.todo.modules.appComponent

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.TodoDao
import com.example.todo.database.TodoDbRepository
import com.example.todo.scopes.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {
    @Binds
    @AppScope
    abstract fun provideRepository(todoDao : TodoDao) : Repository<ListItemModel>
}