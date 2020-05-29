package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.presenter.InjectPresenter

class ListFragment : Fragment(), ListView {

    //private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @InjectPresenter
    lateinit var listPresenter: ListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun setTodoItems(dataset: Array<ListItemModel>) {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = TodoListAdapter(listPresenter.dataset)

        recyclerView.apply { adapter = viewAdapter; layoutManager  = viewManager }
    }


}
