package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ListItemBinding


class TodoListAdapter(private val dataset: Array<ListItemModel>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.item = dataset[position]
    }
}