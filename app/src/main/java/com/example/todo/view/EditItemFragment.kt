package com.example.todo.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.EditItemPresenter
import com.example.todo.mvpViews.EditItemView
import kotlinx.android.synthetic.main.fragment_edit_item.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EditItemFragment : MvpAppCompatFragment(), EditItemView, DatePickerDialog.OnDateSetListener  {
    private val addItemPresenter by moxyPresenter { EditItemPresenter((activity?.application as TodoApplication).repository) }

    private lateinit var date : LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            date = savedInstanceState.getSerializable("date") as LocalDate
        }

        val id = arguments?.getInt("id")
        editItemButton.setOnClickListener {
            if (id != null) {
                addItemPresenter.onClickEditItem(id, titleEditItemText.text.toString(), descriptionEditItemText.text.toString(), date)
            }
        }
        editDateButton.setOnClickListener {onSelectDate()}
    }

    override fun onResume() {
        super.onResume()
        val id = arguments?.getInt("id")
        //в коде появилось много проверок на null, как с ними лучше обращаться?
        // выбрасывать ли исключения, если на фрагмент не пришли нужные аргументы, например?
        if (id != null) {
            addItemPresenter.onSetItemToView(id)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("date", date)
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }

    override fun setItemToView(title: String, description: String, date: LocalDate) {
        //а просто обращение по свойству при этом не срабатывает
        titleEditItemText.setText(title)
        descriptionEditItemText.setText(description)
        this.date = date
        setDateTextView()
    }

    private fun onSelectDate() {
        val datePickerFragment = DatePickerFragment()
        val args = Bundle()
        //можно использовать putSerializable, или есть другие варианты?
        args.putSerializable("date", date)
        datePickerFragment.arguments = args
        datePickerFragment.show(childFragmentManager, "")
    }

    private fun setDateTextView() {
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        dateTextView.text = date.format(formatter)
    }

    //я превратила сам фрагмент в listener, так норм делать?
    //месяц назад, когда я писала курсач, это мне казалось хорошей идеей (:
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = LocalDate.of(year, month, dayOfMonth)
        setDateTextView()
    }
}