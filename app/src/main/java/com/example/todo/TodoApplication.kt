package com.example.todo

import android.app.Application
import com.example.todo.components.AppComponent
import com.example.todo.components.DaggerAppComponent
import com.example.todo.components.DaggerDatabaseComponent
import com.example.todo.components.DatabaseComponent
import com.example.todo.modules.appComponent.AppModule
import javax.inject.Inject

class TodoApplication : Application() {
    @Inject
    lateinit var repository : Repository<ListItemModel>

    lateinit var appComponent: AppComponent
        private set
    lateinit var databaseComponent: DatabaseComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = buildAppComponent()
        databaseComponent = buildDatabaseComponent()
        databaseComponent.inject(this)
        //repository = TodoDbRepository(TodoListRoomDatabase.getDatabase(context = this).todoListDao())//SimpleTodoRepository()
    }

    protected fun buildAppComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    protected fun buildDatabaseComponent() : DatabaseComponent {
        return DaggerDatabaseComponent.builder()
            .appComponent(appComponent)
            .build()
    }
}