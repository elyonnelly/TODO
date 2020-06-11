package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.components.AddItemComponent
import com.example.todo.components.DaggerAddItemComponent
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.ktx.moxyPresenter

class AddItemFragment : ItemFragment() {

    lateinit var addItemComponent: AddItemComponent
        private set

    private val addItemPresenter by moxyPresenter {
        addItemComponent.getPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addItemComponent = DaggerAddItemComponent.builder().build()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editItemButton.setOnClickListener {
            addItemPresenter.onClickAddItem(titleEditItemText.text.toString(), descriptionEditItemText.text.toString(), date)
        }
    }

}