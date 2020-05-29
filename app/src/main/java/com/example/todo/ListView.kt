package com.example.todo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListView : MvpView {
    fun setTodoItems(dataset : Array<ListItemModel>)
}