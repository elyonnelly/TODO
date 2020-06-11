package com.example.todo.components

import android.content.Context
import com.example.todo.TodoApplication
import com.example.todo.modules.appComponent.DaoModule
import com.example.todo.modules.appComponent.DatabaseModule
import com.example.todo.modules.appComponent.RepositoryModule
import com.example.todo.scopes.AddItemFragmentScope
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [DaoModule::class, DatabaseModule::class, RepositoryModule::class])
@AddItemFragmentScope
interface DatabaseComponent {
    fun context() : Context
    fun inject(application : TodoApplication)
}