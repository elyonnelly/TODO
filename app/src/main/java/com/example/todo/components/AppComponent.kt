package com.example.todo.components

import android.content.Context
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.modules.AppModule
import com.example.todo.modules.EditItemModule
import com.example.todo.scopes.AppScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context) : Builder
        fun build() : AppComponent
    }

    fun repository() : Repository<ListItemModel>
}