package com.example.todo.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import java.time.LocalTime

private const val ARG_TIME = "time"

class TimePickerFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_picker, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timePicker = arguments?.getSerializable(ARG_TIME) as LocalTime
        return TimePickerDialog(activity!!,
            parentFragment as TimePickerDialog.OnTimeSetListener,
            timePicker.hour, timePicker.minute, true)

    }

    companion object {

        fun createInstance(time: LocalTime) : TimePickerFragment {
            val timePickerFragment = TimePickerFragment()
            val args = Bundle()
            args.putSerializable(ARG_TIME, time)
            timePickerFragment.arguments = args
            return timePickerFragment
        }
    }
}