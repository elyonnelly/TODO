package com.example.todo.mvpPresenters

import android.content.Intent
import com.example.todo.ListItemModel
import com.example.todo.mvpViews.ListView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class ListPresenter : MvpPresenter<ListView>() {
    lateinit var dataset : Array<ListItemModel>

    override fun onFirstViewAttach() {
        val date = LocalDate.of(1970, 1, 1)
        val item =
            ListItemModel("title", "description", date, false)
        dataset = arrayOf(item, item, item)
        viewState.setTodoItems(dataset)
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