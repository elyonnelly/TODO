package com.example.todo.mvpViews

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.time.LocalDate

interface EditItemView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun goBack()
    @StateStrategyType(SkipStrategy::class)
    fun setItemToView(title : String, description : String, date : LocalDate)
}