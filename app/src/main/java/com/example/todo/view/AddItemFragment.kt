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

    lateinit var date : LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date = if (savedInstanceState == null)
            LocalDate.now()
        else
            savedInstanceState.getSerializable("date") as LocalDate

        setDateTextView(date.year, date.monthValue, date.dayOfMonth)

        addNewItemButton.setOnClickListener { addItemPresenter.onClickAddItem(titleEditText.text.toString(), descriptionEditText.text.toString(), date)}
        selectDateButton.setOnClickListener {onSelectDate()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("date", date)
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    private fun onSelectDate() {
        val datePickerFragment = DatePickerFragment()
        val args = Bundle()
        args.putSerializable("date", date)
        datePickerFragment.arguments = args
        //здесь этот фрагмент должен, получается, добавиться во fragmentManager,
        // и при onCreate он нормально пересоздастся

        fragmentManager?.let { datePickerFragment.show(it, "") }
    }

    private fun setDateTextView(year : Int, month : Int, dayOfMonth : Int) {
        val date = LocalDate.of(year, month, dayOfMonth)
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dateTextView.text = date.format(formatter)
    }
}