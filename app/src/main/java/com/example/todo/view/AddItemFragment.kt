package com.example.todo.view

import android.os.Bundle
import android.view.View
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpViews.ItemView
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.ktx.moxyPresenter

class AddItemFragment : ItemFragment() {

    private val addItemPresenter by moxyPresenter {
        AddItemPresenter((activity?.application as TodoApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editItemButton.setOnClickListener {
            addItemPresenter.onClickAddItem(titleEditItemText.text.toString(), descriptionEditItemText.text.toString(), date)
        }
    }

}