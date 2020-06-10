package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.mvpViews.ItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class EditItemPresenter(id : Int, repository : Repository<ListItemModel>,
                        private val interactor: EditItemInteractor = EditItemInteractor(id, repository))
                        : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        interactor.getItem()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                viewState.setItemToView(data.title, data.description, data.date)
            }
    }

    fun onClickEditItem(newTitle : String, newDescription : String, newDate : LocalDate){
        interactor.editItem(newTitle, newDescription, newDate)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.goBack()
            }
    }
}