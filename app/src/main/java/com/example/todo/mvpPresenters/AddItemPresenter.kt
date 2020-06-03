package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.AddItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class AddItemPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<AddItemView>() {

    fun onClickAddItem(title : String, description : String, date : LocalDate){
        repository.add(ListItemModel(title, description, date, done = false))
        viewState.goBack()
    }
}