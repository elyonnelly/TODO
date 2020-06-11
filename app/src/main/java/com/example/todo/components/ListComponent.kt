package com.example.todo.components

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.components.AppComponent
import com.example.todo.modules.ListModule
import com.example.todo.mvpPresenters.ListPresenter
import com.example.todo.scopes.ListFragmentScope
import com.example.todo.view.ItemParameters
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ListModule::class])
@ListFragmentScope
interface ListComponent {
    fun repository() : Repository<ListItemModel>
    fun getPresenter() : ListPresenter
}