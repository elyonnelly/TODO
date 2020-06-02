package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.*
import com.example.todo.mvpPresenters.ListPresenter
import com.example.todo.mvpViews.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListFragment : MvpAppCompatFragment(), ListView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val listPresenter by moxyPresenter { ListPresenter((this.activity?.application as TodoApplication).repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val addTaskButton = view.findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener {listPresenter.onAddNewItem() }
        return view
    }

    override fun setTodoItems(dataset: Repository<ListItemModel>) {
        viewManager = LinearLayoutManager(this.activity)
        viewAdapter = TodoListAdapter(dataset)

        recyclerView.apply { adapter = viewAdapter; layoutManager  = viewManager }
    }

    override fun addNewItem() {
        val addItemFragment = AddItemFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, addItemFragment)
        transaction?.addToBackStack(null)
        //переходы назад чет не работают

        transaction?.commit()
    }
}