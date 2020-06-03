package com.example.todo

import android.app.Application

class TodoApplication(val repository : Repository<ListItemModel> = TodoRepository()) : Application()