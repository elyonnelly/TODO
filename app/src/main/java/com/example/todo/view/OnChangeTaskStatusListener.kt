package com.example.todo.view

import android.view.View

interface OnChangeTaskStatusListener {
    fun onChangeTaskStatus(view : View, id : Int, status : Boolean)
}