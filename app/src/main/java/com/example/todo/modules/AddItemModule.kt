package com.example.todo.modules

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import dagger.Module
import dagger.Provides

@Module
class AddItemModule {
    @Provides
    @AddItemFragmentScope
    fun provideAddItemInteractor(repository: Repository<ListItemModel>) : AddItemInteractor {
        return AddItemInteractor(repository)
    }

    @Provides
    @AddItemFragmentScope
    fun provideAddItemPresenter(repository: Repository<ListItemModel>) : AddItemPresenter {
        return AddItemPresenter(repository)
    }
}