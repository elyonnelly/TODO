package com.example.todo.modules.EditItemFragment

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class EditInteractorModule(id : Int) {
    @Provides
    @AppScope
    fun provideEditItemInteractor(id : Int, repository: Repository<ListItemModel>) : EditItemInteractor {
        return EditItemInteractor(id, repository)
    }
}