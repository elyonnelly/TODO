package com.example.todo.components

import android.content.Context
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.TodoApplication
import com.example.todo.modules.appComponent.AppModule
import com.example.todo.modules.appComponent.DaoModule
import com.example.todo.modules.appComponent.DatabaseModule
import com.example.todo.modules.appComponent.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DaoModule::class, DatabaseModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun context(context: Context) : Builder
        fun build() : AppComponent
    }

    fun repository() : Repository<ListItemModel>

    fun inject(application : TodoApplication)
}