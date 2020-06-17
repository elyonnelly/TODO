package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.mvpViews.ItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@InjectViewState
class AddItemPresenter(private val interactor: AddItemInteractor) : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setItemToView("", "", LocalDate.now(), LocalTime.now())
    }

    fun onClickAddItem(title : String, description : String, date : LocalDate, time : LocalTime) {
        interactor.addItem(title, description, date, time)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it -> viewState.goBack()
            }
    }
}