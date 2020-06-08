package com.example.todo.mvpViews

import android.view.View
import com.example.todo.ListItemModel
import com.example.todo.Repository
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTodoItems(dataSet: List<ListItemModel>)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToAddNewItemFragment()

    @StateStrategyType(SkipStrategy::class)
    fun navigateToEditNewItemFragment(id : Int)

    @StateStrategyType(SkipStrategy::class)
    fun changeEditClickListener(view : View, id : Int, status : Boolean)
}