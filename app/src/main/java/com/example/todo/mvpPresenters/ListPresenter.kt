package com.example.todo.mvpPresenters

import android.view.View
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(private val repository : Repository<ListItemModel>) : MvpPresenter<ListView>() {

    fun onShowAll() {
        viewState.setTodoItems(repository.getAllItems())
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int){
        viewState.navigateToEditNewItemFragment(id)
    }

    fun onChangeTaskStatus(view : View, id : Int, status : Boolean) {
        val oldItem = repository.get(id)
        repository.update(oldItem.copy(done = status))
        viewState.changeEditClickListener(view, id, status)
    }

    fun onShowActive() {
        val tasks = repository.getAllItems()
        val activeTasks = mutableListOf<ListItemModel>()
        for (task in tasks) {
            if (!task.done) {
                activeTasks.add(task)
            }
        }
        viewState.setTodoItems(activeTasks.toList())
    }

    fun onShowDone() {
        val tasks = repository.getAllItems()
        val doneTasks = mutableListOf<ListItemModel>()
        for (task in tasks) {
            if (task.done) {
                doneTasks.add(task)
            }
        }
        viewState.setTodoItems(doneTasks.toList())
    }
}