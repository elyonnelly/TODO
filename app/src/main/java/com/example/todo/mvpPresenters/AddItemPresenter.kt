package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpViews.ItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class AddItemPresenter(repository : Repository<ListItemModel>,
                       private val interactor: AddItemInteractor = AddItemInteractor(repository))
                        : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setItemToView("", "", LocalDate.now())
    }

    fun onClickAddItem(title : String, description : String, date : LocalDate) {
        interactor.addItem(title, description, date)
        viewState.goBack()
    }
}