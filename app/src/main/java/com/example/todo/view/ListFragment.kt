package com.example.todo.view

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ListItemModel
import com.example.todo.R
import com.example.todo.TodoApplication
import com.example.todo.components.DaggerListComponent
import com.example.todo.components.ListComponent
import com.example.todo.mvpPresenters.ListPresenter
import com.example.todo.mvpViews.ListView
import kotlinx.android.synthetic.main.fragment_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ListFragment : MvpAppCompatFragment(), ListView {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    lateinit var listComponent : ListComponent

    private val listPresenter by moxyPresenter { listComponent.getPresenter() }

    private val clickListener : OnEditClickListener = object : OnEditClickListener {
        override fun onClick(id: Int) {
            listPresenter.onClickEditNewItem(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        listComponent = DaggerListComponent.builder()
            .appComponent((activity?.application as TodoApplication).appComponent)
            .build()
        super.onCreate(savedInstanceState)
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToAddNewItemButton.setOnClickListener {listPresenter.onClickAddNewItem() }
        viewManager = LinearLayoutManager(this.activity)
    }

    override fun onResume() {
        super.onResume()
        listPresenter.onShowAll()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.select_task_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.select_all -> {
                listPresenter.onShowAll()
                return true
            }
            R.id.select_active -> {
                listPresenter.onShowActive()
                return true
            }
            R.id.select_done -> {
                listPresenter.onShowDone()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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