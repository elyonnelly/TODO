package com.example.todo.components

import android.content.Context
import com.example.todo.modules.EditItemModule
import com.example.todo.mvpPresenters.EditItemPresenter
import com.example.todo.scopes.EditItemFragmentScope
import com.example.todo.view.ItemParameters
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [EditItemModule::class])
@EditItemFragmentScope
interface EditItemComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun parameters(parameters: ItemParameters) : Builder
        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build() : EditItemComponent
    }

    fun getPresenter() : EditItemPresenter
}