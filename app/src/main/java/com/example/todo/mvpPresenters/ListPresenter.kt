package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpViews.ListView
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(repository : Repository<ListItemModel>,
                    private val interactor: ListInteractor = ListInteractor(repository))
                    : MvpPresenter<ListView>() {

    fun onShowAll() {
        setTodoItems { interactor.getAll() }
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int) {
        interactor.checkTaskStatus(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { status ->
                if (!status) {
                    viewState.navigateToEditNewItemFragment(id)
                }
            }
    }

    fun onChangeTaskStatus(id : Int, status : Boolean) {
        interactor.changeTaskStatus(id, status).subscribe()
    }

    fun onShowActive() {
        setTodoItems { interactor.getActive() }
    }

    fun onShowDone() {
        setTodoItems { interactor.getDone() }
    }

    private fun setTodoItems(func: () -> Single<List<ListItemModel>>) {
        func.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> viewState.setTodoItems(items) }
    }
}