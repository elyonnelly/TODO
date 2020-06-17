package com.example.todo.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.todo.R
import com.example.todo.mvpViews.ItemView
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.MvpAppCompatFragment
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private const val INSTANCE_STATE_DATE = "date"
private const val INSTANCE_STATE_TIME = "time"

open class ItemFragment : MvpAppCompatFragment(), ItemView,
                            DatePickerDialog.OnDateSetListener,
                            TimePickerDialog.OnTimeSetListener {

    protected var date: LocalDate = LocalDate.now()
    protected var time : LocalTime = LocalTime.now()

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
            time = savedInstanceState.getSerializable(INSTANCE_STATE_TIME) as LocalTime
        }
        updateDateTextView()

        selectDateButton.setOnClickListener {onSelectDate()}
        selectTimeButton.setOnClickListener {onSelectTime()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(INSTANCE_STATE_DATE, date)
        outState.putSerializable(INSTANCE_STATE_TIME, time)
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    override fun setItemToView(title: String, description: String, date: LocalDate, time : LocalTime) {
        titleEditItemText.setText(title)
        descriptionEditItemText.setText(description)
        this.date = date
        this.time = time
        updateDateTextView()
        updateTimeTextView()
    }

    private fun onSelectDate() {
        val datePickerFragment = DatePickerFragment.createInstance(date)
        datePickerFragment.show(childFragmentManager, "")
    }

    private fun onSelectTime() {
        val timePickerFragment = TimePickerFragment.createInstance(time)
        timePickerFragment.show(childFragmentManager, "")
    }

    private fun updateDateTextView() {
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dateTextView.text = date.format(formatter)
    }

    private fun updateTimeTextView() {
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        timeTextView.text = time.format(formatter)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = LocalDate.of(year, month, dayOfMonth)
        updateDateTextView()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        time = LocalTime.of(hourOfDay, minute)
        updateTimeTextView()
    }
}