package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.mvpViews.ItemView
import com.example.todo.view.ItemParameters
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate
import java.time.LocalTime

@InjectViewState
class EditItemPresenter(private val interactor: EditItemInteractor) : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        interactor.getItem()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                viewState.setItemToView(data.title, data.description, data.date, data.time)
            }
    }

    fun onClickEditItem(newTitle : String, newDescription : String, newDate : LocalDate, newTime : LocalTime){
        interactor.editItem(newTitle, newDescription, newDate, newTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.goBack()
            }
    }
}