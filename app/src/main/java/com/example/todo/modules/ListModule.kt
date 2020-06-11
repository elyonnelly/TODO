package com.example.todo.modules

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.ListInteractor
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpPresenters.ListPresenter
import com.example.todo.scopes.AddItemFragmentScope
import com.example.todo.scopes.ListFragmentScope
import dagger.Module
import dagger.Provides

@Module
class ListModule {
    @Provides
    @ListFragmentScope
    fun provideListPresenter(repository: Repository<ListItemModel>) : ListPresenter {
        return ListPresenter(repository)
    }

    @Provides
    @ListFragmentScope
    fun provideListInteractor(repository: Repository<ListItemModel>) : ListInteractor {
        return ListInteractor(repository)
    }
}