package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.EditItemPresenter
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.ktx.moxyPresenter

private const val ARG_ID = "id"

class EditItemFragment : ItemFragment()  {

    private val editItemPresenter by moxyPresenter {
        EditItemPresenter(getIdFromArgument(),(activity?.application as TodoApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editItemButton.setOnClickListener {
            editItemPresenter.onClickEditItem(titleEditItemText.text.toString(),
                                descriptionEditItemText.text.toString(),
                                date)
        }
    }

    private fun getIdFromArgument() : Int {
        val id = arguments?.getInt("id")
        return id!!
    }

    companion object {
        @JvmStatic
        fun newInstance(id : Int) =
            EditItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }
}