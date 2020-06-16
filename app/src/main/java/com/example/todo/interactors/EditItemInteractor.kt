package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.database.copyToModel
import com.example.todo.view.ItemParameters
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate

class EditItemInteractor(private val parameters : ItemParameters,
                         private val repository : Repository<ListItemModel>) {

    fun getItem() : Single<ListItemModel> {
        return repository.get(parameters.id)
            .map { it.copyToModel() }
    }

    fun editItem(newTitle : String, newDescription : String, newDate : LocalDate): Completable {
        return repository.get(parameters.id)
            .map { entity -> entity.copyToModel() }
            .flatMapCompletable {
                repository.update(it.copy(title = newTitle, description = newDescription, date = newDate))
            }
    }
}