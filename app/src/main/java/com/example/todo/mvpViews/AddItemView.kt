package com.example.todo.mvpViews

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddItemView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addItem()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDate()
}