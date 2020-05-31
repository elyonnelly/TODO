package com.example.todo

import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate
import java.util.*

@InjectViewState
class ListPresenter() : MvpPresenter<ListView>() {
    lateinit var dataset : Array<ListItemModel>

    override fun onFirstViewAttach() {
        val date = LocalDate.of(1970, 1, 1)
        val item = ListItemModel("title", "description", date, false)
        dataset = arrayOf(item, item, item)
        viewState.setTodoItems(dataset)
    }
}