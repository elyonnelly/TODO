package com.example.todo.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpViews.AddItemView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_item.*
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddItemFragment : MvpAppCompatFragment(), AddItemView {
    private val addItemPresenter by moxyPresenter { AddItemPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)
        val goBackButton = view.findViewById<FloatingActionButton>(R.id.goBackButton)
        goBackButton.setOnClickListener { addItemPresenter.onAddItem()}
        return view
    }

    override fun addItem() {
    }

    override fun setDate() {
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        val dateString :String = date.format(formatter)
        editTextDate.setText(dateString)
    }

}