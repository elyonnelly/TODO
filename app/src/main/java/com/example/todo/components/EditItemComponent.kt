package com.example.todo.components

import dagger.Component

@Component(dependencies = [AppComponent::class])
interface EditItemComponent {

    //здесь cделать builder, который использует параметры с id
}