package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<ListView>() {

    override fun onFirstViewAttach() {
        viewState.setTodoItems(repository.getAllItems())
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int){
        viewState.navigateToEditNewItemFragment(id)
    }
}