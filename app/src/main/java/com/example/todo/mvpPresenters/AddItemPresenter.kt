package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.AddItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@InjectViewState
class AddItemPresenter(private val dataSet : Repository<ListItemModel>) : MvpPresenter<AddItemView>() {

    fun onClickAddItem(title : String, description : String, dateString : String){
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dataSet.update(ListItemModel(title, description, LocalDate.parse(dateString, formatter), done = false))
        viewState.goBack()
    }
}