package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.mvpViews.ItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate

@InjectViewState
class EditItemPresenter(id : Int, repository : Repository<ListItemModel>,
                        private val interactor: EditItemInteractor = EditItemInteractor(id, repository))
                        : MvpPresenter<ItemView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Observable.fromCallable {
            interactor.getItem()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                viewState.setItemToView(data.title, data.description, data.date)
            }
        //val data = interactor.getItem()
        //viewState.setItemToView(data.title, data.description, data.date)
    }

    fun onClickEditItem(newTitle : String, newDescription : String, newDate : LocalDate){
        Observable.fromCallable {interactor.editItem(newTitle, newDescription, newDate)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        //interactor.editItem(newTitle, newDescription, newDate)
        viewState.goBack()
    }
}