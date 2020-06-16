package com.example.todo

import android.app.Application
import com.example.todo.components.AppComponent
import com.example.todo.components.DaggerAppComponent
import com.example.todo.modules.EditItemModule
import io.reactivex.android.schedulers.AndroidSchedulers

class TodoApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = buildAppComponent()
    }

    private fun buildAppComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .context(this)
            .build()
    }

}