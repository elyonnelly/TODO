package com.example.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ListItemModel
import com.example.todo.R
import java.time.format.DateTimeFormatter


class TodoListAdapter(private val dataset: Array<ListItemModel>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var title : TextView = view.findViewById(R.id.title)
        var description : TextView = view.findViewById(R.id.description)
        var date : TextView = view.findViewById(R.id.date)
        var done : CheckBox = view.findViewById(R.id.done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.title.text = dataset[position].title
        holder.description.text = dataset[position].description
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        val dateString :String = dataset[position].date.format(formatter)
        holder.date.text = dateString
        holder.done.isChecked = dataset[position].done
    }
}