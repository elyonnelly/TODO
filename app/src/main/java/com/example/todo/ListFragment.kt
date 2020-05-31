package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.MvpAppCompatFragment
import moxy.MvpFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ListFragment : MvpAppCompatFragment(), ListView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @InjectPresenter
    internal lateinit var listPresenter: ListPresenter

    @ProvidePresenter
    internal fun providePresenter() = ListPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, true)
    }

    override fun setTodoItems(dataset: Array<ListItemModel>) {
        viewManager = LinearLayoutManager(this.activity)
        viewAdapter = TodoListAdapter(listPresenter.dataset)

        recyclerView.apply { adapter = viewAdapter; layoutManager  = viewManager }
    }
}
