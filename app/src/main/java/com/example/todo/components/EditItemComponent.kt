package com.example.todo.components

import com.example.todo.ListItemModel
import com.example.todo.Repository
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
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : EditItemComponent
    }

    fun repository() : Repository<ListItemModel>
    fun getPresenter() : EditItemPresenter
}