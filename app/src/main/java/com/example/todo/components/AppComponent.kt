package com.example.todo.components

import android.content.Context
import com.example.todo.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun context() : Context
}