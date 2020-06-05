package com.example.todo

interface Repository<T> {
    val size : Int
    fun get(id : Int) : T
    fun add(value : T)
    fun update(value : T)
    fun remove(id : Int)
    fun getAllItems() : List<ListItemModel>
}