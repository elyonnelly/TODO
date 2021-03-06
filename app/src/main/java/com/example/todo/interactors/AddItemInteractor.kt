package com.example.todo.interactors

import com.example.todo.ListItemModel
import com.example.todo.Repository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import javax.inject.Inject

class AddItemInteractor constructor(private val repository : Repository<ListItemModel>) {

    fun addItem(title : String, description : String, date : LocalDate) : Single<Long> {
        return repository.add(ListItemModel(0, title, description, date, false))
    }
}