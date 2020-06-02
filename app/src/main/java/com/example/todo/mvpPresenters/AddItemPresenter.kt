package com.example.todo.mvpPresenters

import android.widget.DatePicker
import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpViews.AddItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class AddItemPresenter(private val dataSet : Repository<ListItemModel>) : MvpPresenter<AddItemView>() {
    fun onAddItem(){
        viewState.addItemAction()
    }

    fun addItem(title : String, description : String, date : LocalDate) {
        dataSet.update(ListItemModel(title, description, date, done = false))
    }

}