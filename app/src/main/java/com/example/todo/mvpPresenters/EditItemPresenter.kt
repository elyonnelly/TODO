package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.EditItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class EditItemPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<EditItemView>() {

    fun onClickEditItem(id : Int, title : String, description : String, date : LocalDate){
        repository.update(id, ListItemModel(title, description, date, done = false))
        viewState.goBack()
    }

    fun onSetItemToView(id : Int) {
        val data = repository.get(id)
        viewState.setItemToView(data.title, data.description, data.date)
    }
}