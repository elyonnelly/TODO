package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ListItemModel
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.mvpPresenters.ListPresenter
import com.example.todo.mvpViews.ListView
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ListFragment : MvpAppCompatFragment(), ListView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val listPresenter by moxyPresenter { ListPresenter((this.activity?.application as TodoApplication).repository) }

    private val clickListener : OnEditClickListener = object : OnEditClickListener {
        override fun onClick(id: Int) {
            listPresenter.onClickEditNewItem(id)
        }
    }

    private val changeTaskStatusListener : OnChangeTaskStatusListener = object  : OnChangeTaskStatusListener {
        override fun onChangeTaskStatus(id: Int, status: Boolean) {
            listPresenter.onChangeTaskStatus(id, status)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToAddNewItemButton.setOnClickListener {listPresenter.onClickAddNewItem() }
        viewManager = LinearLayoutManager(this.activity)
    }

    override fun onResume() {
        super.onResume()
        listPresenter.onSetTodoItems()
    }

    override fun setTodoItems(dataSet: List<ListItemModel>) {
        viewAdapter = TodoListAdapter(dataSet, clickListener, changeTaskStatusListener)
        recyclerView.apply { adapter = viewAdapter; layoutManager  = viewManager }
    }

    override fun navigateToAddNewItemFragment() {
        navigateTo(AddItemFragment())
    }

    override fun navigateToEditNewItemFragment(id: Int) {
        navigateTo(EditItemFragment.newInstance(id))
    }

    private fun navigateTo(fragment: MvpAppCompatFragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)

        transaction?.setReorderingAllowed(true)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}