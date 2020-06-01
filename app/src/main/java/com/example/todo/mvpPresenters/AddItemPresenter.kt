package com.example.todo.mvpPresenters

import com.example.todo.mvpViews.AddItemView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AddItemPresenter : MvpPresenter<AddItemView>() {
    fun onAddItem(){}

    override fun onFirstViewAttach() {
        viewState.setDate()
    }
}