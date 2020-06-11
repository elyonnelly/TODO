package com.example.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.TodoApplication
import com.example.todo.components.AddItemComponent
import com.example.todo.components.AppComponent
import com.example.todo.mvpPresenters.AddItemPresenter
import com.example.todo.mvpViews.ItemView
import kotlinx.android.synthetic.main.fragment_item.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class AddItemFragment : ItemFragment() {

    lateinit var addItemComponent: AddItemComponent
        private set

    @Inject
    lateinit var addItemPresenter : AddItemPresenter

    //а как вообще быть, если мне презентеры создает moxy
    //private val addItemPresenter by moxyPresenter {
     //   AddItemPresenter((activity?.application as TodoApplication).repository)
    //}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addItemComponent = DaggerAddItemComponent.builder().build()
        addItemComponent.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editItemButton.setOnClickListener {
            addItemPresenter.onClickAddItem(titleEditItemText.text.toString(), descriptionEditItemText.text.toString(), date)
        }
    }

}