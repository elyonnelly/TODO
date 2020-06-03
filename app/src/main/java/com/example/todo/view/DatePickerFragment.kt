package com.example.todo.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import kotlinx.android.synthetic.main.list_item.*
import java.time.LocalDate

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener  {

    private lateinit var date : LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        date = arguments?.getSerializable("date") as LocalDate
        return DatePickerDialog(activity as Context,this,  date.year, date.monthValue, date.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        (parentFragment as AddItemFragment).date = LocalDate.of(year, month, dayOfMonth)
    }
}