package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.ListItemEntity
import com.example.todo.database.copyToModel
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ListInteractor(private val repository : Repository<ListItemModel>) {

    fun getAll() : Single<List<ListItemModel>> {
        return repository.getAll()
            .subscribeOn(Schedulers.io())
            .map {
                    entities -> entities.map { entity -> entity.copyToModel() }
            }
    }

    fun getActive() : Single<List<ListItemModel>> {
        return repository.getAll()
            .subscribeOn(Schedulers.io())
            .map {
                    entities -> entities.map { entity -> entity.copyToModel() }
                                        .filter { item -> !item.done }
            }
    }

    fun getDone() : Single<List<ListItemModel>> {
        return repository.getAll()
            .subscribeOn(Schedulers.io())
            .map {
                    entities -> entities.map { entity -> entity.copyToModel() }
                                        .filter { item -> item.done }
            }
    }

    fun changeTaskStatus(id : Int, status : Boolean) : Completable {
        return repository.get(id)
            .subscribeOn(Schedulers.io())
            .map { entity -> entity.copyToModel() }
            .flatMapCompletable {
                repository.update(it.copy(done = status))
            }
    }

    fun checkTaskStatus(id : Int) : Single<Boolean> {
        return repository.get(id)
                .subscribeOn(Schedulers.io())
                .map { it.done }
    }
}
