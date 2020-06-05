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

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment()  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val datePickerDate = arguments?.getSerializable(ARG_DATE) as LocalDate
        return DatePickerDialog(activity!!,
                parentFragment as DatePickerDialog.OnDateSetListener,
                datePickerDate.year, datePickerDate.monthValue, datePickerDate.dayOfMonth)
    }

    companion object {

        fun createInstance(date : LocalDate) : DatePickerFragment {
            val datePickerFragment = DatePickerFragment()
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            datePickerFragment.arguments = args
            return datePickerFragment
        }
    }
}