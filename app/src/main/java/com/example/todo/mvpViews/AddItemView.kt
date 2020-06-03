package com.example.todo.mvpViews

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddItemView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun goBack()
}