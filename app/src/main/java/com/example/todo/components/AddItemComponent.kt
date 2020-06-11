package com.example.todo.components

import com.example.todo.interactors.AddItemInteractor
import com.example.todo.modules.AddItemFragment.AddItemInteractorModule
import com.example.todo.modules.AddItemFragment.AddItemPresenterModule
import com.example.todo.scopes.AddItemFragmentScope
import com.example.todo.view.AddItemFragment
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [AddItemInteractorModule::class, AddItemPresenterModule::class])
@AddItemFragmentScope
interface AddItemComponent {
    fun inject(addItemFragment: AddItemFragment)
}