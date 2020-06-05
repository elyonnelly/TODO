package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class EditItemPresenter(private val id : Int, private val repository : Repository<ListItemModel>) : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val data = repository.get(id)
        viewState.setItemToView(data.title, data.description, data.date)
    }

    fun onClickEditItem(newTitle : String, newDescription : String, newDate : LocalDate){
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(title = newTitle, description = newDescription, date = newDate))
        viewState.goBack()
    }
}