package com.example.todo.modules

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpPresenters.EditItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import com.example.todo.scopes.EditItemFragmentScope
import com.example.todo.view.ItemParameters
import dagger.Module
import dagger.Provides

@Module
class EditItemModule {

    @Provides
    @EditItemFragmentScope
    fun provideEditItemInteractor(parameters: ItemParameters, repository: Repository<ListItemModel>) : EditItemInteractor {
        return EditItemInteractor(parameters, repository)
    }

    @Provides
    @EditItemFragmentScope
    fun provideEditItemPresenter(interactor: EditItemInteractor) : EditItemPresenter {
        return EditItemPresenter(interactor)
    }
}