package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.AddItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@InjectViewState
class AddItemPresenter(private val dataSet : Repository<ListItemModel>) : MvpPresenter<AddItemView>() {

    fun onClickAddItem(title : String, description : String, dateString : String){
        val format = SimpleDateFormat()
        format.applyPattern("dd LLLL yyyy")
        val date = format.parse(dateString) as Date

        dataSet.update(ListItemModel(title, description, date, done = false))
        viewState.goBack()
    }
}