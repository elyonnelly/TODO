package com.example.todo

interface Repository<T> {
    fun get(i : Int) : T
    fun update()
    fun remove(i : Int)
}