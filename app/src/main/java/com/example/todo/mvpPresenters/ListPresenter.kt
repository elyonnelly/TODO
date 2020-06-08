package com.example.todo.mvpPresenters

import android.view.View
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(repository : Repository<ListItemModel>,
                    val interactor: ListInteractor = ListInteractor(repository))
                    : MvpPresenter<ListView>() {

    fun onShowAll() {
        viewState.setTodoItems(interactor.getAll())
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int){
        viewState.navigateToEditNewItemFragment(id)
    }

    fun onChangeTaskStatus(view : View, id : Int, status : Boolean) {
        interactor.changeTaskStatus(id, status)
        viewState.changeEditClickListener(view, id, status)
    }

    fun onShowActive() {
        viewState.setTodoItems(interactor.getActive())
    }

    fun onShowDone() {
        viewState.setTodoItems(interactor.getDone())
    }
}