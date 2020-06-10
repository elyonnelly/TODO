package com.example.todo.components

import android.content.Context
import com.example.todo.TodoApplication
import com.example.todo.modules.DaoModule
import com.example.todo.modules.DatabaseModule
import com.example.todo.modules.RepositoryModule
import com.example.todo.scopes.DatabaseScope
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [AppComponent::class], modules = [DaoModule::class, DatabaseModule::class, RepositoryModule::class])
@DatabaseScope
interface DatabaseComponent {
    fun context() : Context
    fun inject(application : TodoApplication)
}