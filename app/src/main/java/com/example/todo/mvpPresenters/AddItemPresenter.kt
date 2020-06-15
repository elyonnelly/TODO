package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.mvpViews.ItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate
import javax.inject.Inject

@InjectViewState
class AddItemPresenter(private val interactor: AddItemInteractor) : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setItemToView("", "", LocalDate.now())
    }

    fun onClickAddItem(title : String, description : String, date : LocalDate) {
        interactor.addItem(title, description, date)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.goBack()
            }
    }
}