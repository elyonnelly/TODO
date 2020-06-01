package com.example.todo.mvpViews

import com.example.todo.ListItemModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTodoItems(dataset : Array<ListItemModel>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addNewItem()
}