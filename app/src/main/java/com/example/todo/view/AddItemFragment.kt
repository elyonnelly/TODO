package com.example.todo.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpViews.AddItemView
import kotlinx.android.synthetic.main.fragment_add_item.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddItemFragment : MvpAppCompatFragment(), AddItemView {
    private val addItemPresenter by moxyPresenter { AddItemPresenter((activity?.application as TodoApplication).repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val today = LocalDate.now()
        setDateTextView(today.year, today.monthValue, today.dayOfMonth)

        addNewItemButton.setOnClickListener { addItemPresenter.onClickAddItem(titleEditText.text.toString(), descriptionEditText.text.toString(), dateTextView.text.toString())}
        selectDateButton.setOnClickListener {onSelectDate()}
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    private fun onSelectDate() {
        val today = LocalDate.now()
        context?.let { it1 ->
            DatePickerDialog(
                it1,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    setDateTextView(year, monthOfYear, dayOfMonth)
                },
                today.year, today.monthValue, today.dayOfMonth).show()
        }
    }

    private fun setDateTextView(year : Int, month : Int, dayOfMonth : Int) {
        val date = LocalDate.of(year, month, dayOfMonth)
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dateTextView.text = date.format(formatter)
    }
}