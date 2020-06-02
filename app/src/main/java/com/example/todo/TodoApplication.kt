package com.example.todo

import android.app.Application
import java.time.LocalDate
import java.util.*

class TodoApplication(val repository : Repository<ListItemModel> = TodoRepository()) : Application() {

    override fun onCreate() {
        super.onCreate()
        val date = Date()
        val item =  ListItemModel("title", "description", date, false)
        //for (i in 1..3) {
         //   repository.update(item)
        //}
    }
}