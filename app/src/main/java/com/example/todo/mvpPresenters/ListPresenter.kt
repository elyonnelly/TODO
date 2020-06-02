package com.example.todo.mvpPresenters

import android.content.Intent
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class ListPresenter(private val dataSet : Repository<ListItemModel>) : MvpPresenter<ListView>() {

    override fun onFirstViewAttach() {
        viewState.setTodoItems(dataSet)
    }

    fun onAddNewItem(){
        viewState.addNewItem()
    }

    fun handleAddItemResult(data : Intent?){
        //здесь нужно передать в dataset from adapter новую информацию
        //dataset лежит в ListPresenter'e
        //у меня есть presenter для activity и для fragment на нем. звучит плохо.
    }
}