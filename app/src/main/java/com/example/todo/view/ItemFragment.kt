package com.example.todo.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import com.example.todo.R
import com.example.todo.mvpViews.ItemView
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.MvpAppCompatFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val INSTANCE_STATE_DATE = "date"

open class ItemFragment : MvpAppCompatFragment(), ItemView, DatePickerDialog.OnDateSetListener  {

    protected var date: LocalDate = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            date = savedInstanceState.getSerializable(INSTANCE_STATE_DATE) as LocalDate
        }

        selectDateButton.setOnClickListener {onSelectDate()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(INSTANCE_STATE_DATE, date)
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    override fun setItemToView(title: String, description: String, date: LocalDate) {
        titleEditItemText.setText(title)
        descriptionEditItemText.setText(description)
        this.date = date
        updateDateTextView()
    }

    private fun onSelectDate() {
        val datePickerFragment = DatePickerFragment.createInstance(date)
        datePickerFragment.show(childFragmentManager, "")
    }

    private fun updateDateTextView() {
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dateTextView.text = date.format(formatter)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = LocalDate.of(year, month, dayOfMonth)
        updateDateTextView()
    }
}