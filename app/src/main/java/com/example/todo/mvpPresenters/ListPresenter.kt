package com.example.todo.mvpPresenters

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpViews.ListView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(repository : Repository<ListItemModel>,
                    private val interactor: ListInteractor = ListInteractor(repository))
                    : MvpPresenter<ListView>() {

    fun onShowAll() {
        setTodoItems { interactor.getAll() }
        /*Observable.create<List<ListItemModel>> {
            subscriber -> subscriber.onNext(interactor.getAll())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> viewState.setTodoItems(items)}*/
    }

    fun onClickAddNewItem(){
        viewState.navigateToAddNewItemFragment()
    }

    fun onClickEditNewItem(id : Int) {
        Observable.create<Boolean> {
            subscriber -> subscriber.onNext(interactor.checkTaskStatus(id))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { status ->
            if (!status) {
                viewState.navigateToEditNewItemFragment(id)
            }
        }
    }

    fun onChangeTaskStatus(id : Int, status : Boolean) {
        Thread(Runnable {interactor.changeTaskStatus(id, status)}).start()
    }

    fun onShowActive() {
        setTodoItems { interactor.getActive() }
        //viewState.setTodoItems(interactor.getActive())
    }

    fun onShowDone() {
        setTodoItems { interactor.getDone() }
        //viewState.setTodoItems(interactor.getDone())
    }

    private fun setTodoItems(func: () -> List<ListItemModel>) {
        Observable.fromCallable {func.invoke()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> viewState.setTodoItems(items)}
    }
}