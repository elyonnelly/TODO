package com.example.todo.modules.ListFragment

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.interactors.ListInteractor
import dagger.Module
import dagger.Provides

@Module
class ListInteractorModule {
    @Provides
    fun provideListInteractor(repository: Repository<ListItemModel>) : ListInteractor {
        return ListInteractor(repository)
    }
}