package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class AddItemPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setItemToView("", "", LocalDate.now())
    }

    fun onClickAddItem(title : String, description : String, date : LocalDate) {
        repository.add(ListItemModel(title, description, date, done = false))
        viewState.goBack()
    }
}