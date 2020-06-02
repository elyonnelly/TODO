package com.example.todo.mvpViews

import com.example.todo.ListItemModel
import com.example.todo.Repository
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTodoItems(dataset : Repository<ListItemModel>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addNewItem()
}