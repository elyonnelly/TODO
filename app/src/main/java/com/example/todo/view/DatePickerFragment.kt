package com.example.todo.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import java.time.LocalDate

class DatePickerFragment : DialogFragment()  {

    private lateinit var datePickerDate : LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        datePickerDate = arguments?.getSerializable("date") as LocalDate
        return DatePickerDialog(activity as Context,
            parentFragment as AddItemFragment,
            datePickerDate.year, datePickerDate.monthValue, datePickerDate.dayOfMonth)
    }
}