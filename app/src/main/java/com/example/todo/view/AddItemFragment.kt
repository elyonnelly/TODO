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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.title
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddItemFragment : MvpAppCompatFragment(), AddItemView {
    private val addItemPresenter by moxyPresenter { AddItemPresenter((this.activity?.application as TodoApplication).repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)
        val goBackButton = view.findViewById<FloatingActionButton>(R.id.goBackButton)
        goBackButton.setOnClickListener { addItemPresenter.onAddItem()}
        //val datePicker
        return view
    }

    override fun addItemAction() {
        datePicker.date
        addItemPresenter.addItem(titleEditText.text.toString(), descriptionEditText.text.toString(), datePicker.getDate())

        val listFragment = ListFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, listFragment)

        transaction?.commit()
    }

    fun DatePicker.getDate(): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }

}