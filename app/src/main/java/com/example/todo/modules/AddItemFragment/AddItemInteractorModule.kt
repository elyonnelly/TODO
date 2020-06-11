package com.example.todo.modules.AddItemFragment

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.scopes.AddItemFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class AddItemInteractorModule {
    @Binds
    @AddItemFragmentScope
    abstract fun provideAddItemInteractor(repository: Repository<ListItemModel>) : AddItemInteractor
}