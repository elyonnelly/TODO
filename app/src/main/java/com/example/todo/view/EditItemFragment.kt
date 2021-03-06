package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.components.DaggerEditItemComponent
import com.example.todo.components.EditItemComponent
import com.example.todo.mvpPresenters.EditItemPresenter
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.ktx.moxyPresenter

private const val ARG_ID = "id"

class EditItemFragment : ItemFragment()  {

    lateinit var editItemComponent: EditItemComponent
        private set

    private val editItemPresenter by moxyPresenter {
        editItemComponent.getPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        editItemComponent = DaggerEditItemComponent.builder()
            .appComponent((activity?.application as TodoApplication).appComponent)
            .parameters(ItemParameters(getIdFromArgument()))
            .build()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editItemButton.setOnClickListener {
            editItemPresenter.onClickEditItem(titleEditItemText.text.toString(),
                                descriptionEditItemText.text.toString(),
                                date)
        }
    }

    private fun getIdFromArgument() : Long {
        val id = arguments?.getLong("id")
        return id!!
    }

    companion object {
        fun newInstance(id : Long) =
            EditItemFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ID, id)
                }
            }
    }
}