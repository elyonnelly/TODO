package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<ListView>() {

    fun onSetTodoItems() {
        viewState.setTodoItems(repository.getAllItems())
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int){
        viewState.navigateToEditNewItemFragment(id)
    }

    fun onChangeTaskStatus(id : Int, status : Boolean) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(done = status))
    }
}