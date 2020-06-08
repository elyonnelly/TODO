package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.mvpViews.ItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class EditItemPresenter(id : Int, repository : Repository<ListItemModel>,
                        val interactor: EditItemInteractor = EditItemInteractor(id, repository))
                        : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val data = interactor.getItem()
        viewState.setItemToView(data.title, data.description, data.date)
    }

    fun onClickEditItem(newTitle : String, newDescription : String, newDate : LocalDate){
        interactor.editItem(newTitle, newDescription, newDate)
        viewState.goBack()
    }
}