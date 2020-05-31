package com.example.todo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTodoItems(dataset : Array<ListItemModel>)
}