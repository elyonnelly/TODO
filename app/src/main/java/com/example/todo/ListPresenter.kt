package com.example.todo

import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class ListPresenter() : MvpPresenter<ListView>() {
    lateinit var dataset : Array<ListItemModel>

    override fun onFirstViewAttach() {
        val item = ListItemModel("title", "description", LocalDate.of(1970, 1, 1), false)
        dataset = arrayOf(item, item, item)
        viewState.setTodoItems(dataset)
    }
}