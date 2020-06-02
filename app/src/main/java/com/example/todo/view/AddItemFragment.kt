package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpViews.AddItemView
import kotlinx.android.synthetic.main.fragment_add_item.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate

class AddItemFragment : MvpAppCompatFragment(), AddItemView {
    private val addItemPresenter by moxyPresenter { AddItemPresenter((this.activity?.application as TodoApplication).repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewItemButton.setOnClickListener { addItemPresenter.onClickAddItem(titleEditText.text.toString(), descriptionEditText.text.toString(), datePicker.getDate())}
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    private fun DatePicker.getDate(): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }
}