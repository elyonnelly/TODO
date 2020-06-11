package com.example.todo.modules.AddItemFragment

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class AddItemPresenterModule {
    @Binds
    @AddItemFragmentScope
    abstract fun provideAddItemPresenter(repository: Repository<ListItemModel>) : AddItemPresenter

}