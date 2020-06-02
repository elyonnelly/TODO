package com.example.todo

interface Repository<T> {
    val size : Int
    fun get(i : Int) : T
    fun update(value : T)
    fun remove(i : Int)
    fun getAllItems() : MutableList<T>
}