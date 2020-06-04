package com.example.todo.view

interface OnChangeTaskStatusListener {
    fun onChangeTaskStatus(id : Int, status : Boolean)
}