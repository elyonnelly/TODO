package com.example.todo.modules

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.TodoDbRepository
import com.example.todo.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    @AppScope
    abstract fun provideRepository(todoDbRepository: TodoDbRepository) : Repository<ListItemModel>
}