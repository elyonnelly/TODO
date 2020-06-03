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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    //при пересоздании вьюха сама справляется запоминать, какая в ней дата?
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val datePickerDate = arguments?.getSerializable("date") as LocalDate
        return if (parentFragment is AddItemFragment) {
            DatePickerDialog(activity as Context,
                parentFragment as AddItemFragment,
                datePickerDate.year, datePickerDate.monthValue, datePickerDate.dayOfMonth)
        } else {
            DatePickerDialog(activity as Context,
                parentFragment as EditItemFragment,
                datePickerDate.year, datePickerDate.monthValue, datePickerDate.dayOfMonth)
        }
    }
}