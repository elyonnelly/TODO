package com.example.todo.components

import com.example.todo.modules.AddItemModule
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.scopes.AddItemFragmentScope
import com.example.todo.view.ItemParameters
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [AddItemModule::class])
@AddItemFragmentScope
interface AddItemComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build() : AddItemComponent
    }

    fun getPresenter() : AddItemPresenter
}