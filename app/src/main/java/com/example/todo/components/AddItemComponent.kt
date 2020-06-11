package com.example.todo.components

import com.example.todo.ListItemModel
import com.example.todo.Repository
import com.example.todo.modules.AddItemModule
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import com.example.todo.view.ItemParameters
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [AddItemModule::class])
@AddItemFragmentScope
interface AddItemComponent {
    fun repository() : Repository<ListItemModel>
    fun getPresenter() : AddItemPresenter
}