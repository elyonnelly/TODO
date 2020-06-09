package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpViews.ItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
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
        Observable.fromCallable {
            interactor.addItem(title, description, date)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        //interactor.addItem(title, description, date)
        viewState.goBack()
    }
}