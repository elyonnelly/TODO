package com.example.todo.view

import android.view.View

interface OnChangeTaskStatusListener {
    fun onChangeTaskStatus(id : Long, status : Boolean)
}