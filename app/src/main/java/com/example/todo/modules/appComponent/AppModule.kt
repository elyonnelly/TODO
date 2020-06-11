package com.example.todo.modules.appComponent

import android.content.Context
import com.example.todo.components.AppComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//убрать конструктор
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() : Context {
        return context
    }
}