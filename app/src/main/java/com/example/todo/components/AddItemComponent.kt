package com.example.todo.components

import com.example.todo.modules.AddItemModule
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [AddItemModule::class])
@AddItemFragmentScope
interface AddItemComponent {

    fun getPresenter() : AddItemPresenter
}