package com.example.todo.modules

import android.content.Context
import com.example.todo.database.TodoDao
import com.example.todo.database.TodoListRoomDatabase
import com.example.todo.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
//убрать конструктор
class AppModule {
    /*@Provides
    @Singleton
    fun provideContext() : Context {
        return context
    }*/

    @Provides
    @AppScope
    //это объявляется зависимость
    fun provideTodoListRoomDatabase(context : Context) : TodoListRoomDatabase {
        //мы говорим, найди мне зависимость на context где-нибудь
        return TodoListRoomDatabase.getDatabase(context)
    }

    @Provides
    @AppScope
    fun provideDao(database : TodoListRoomDatabase) : TodoDao {
        return database.todoListDao()
    }
}