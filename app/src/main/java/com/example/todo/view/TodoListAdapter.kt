package com.example.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ListItemModel
import com.example.todo.R
import com.example.todo.Repository
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


class TodoListAdapter(private val dataSet: List<ListItemModel>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    //а эти присваивания происходят до вызова базового конструктора? это равносильно конструктору, да?
    class TodoViewHolder(view : View,
                         val title : TextView = view.findViewById(R.id.title),
                         val description : TextView = view.findViewById(R.id.description),
                         val date : TextView = view.findViewById(R.id.date),
                         val done : CheckBox = view.findViewById(R.id.done))
                        : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.description.text = dataSet[position].description
        val formatter = SimpleDateFormat()
        formatter.applyPattern("dd LLLL yyyy")
        val dateString :String = formatter.format(dataSet[position].date)
        holder.date.text = dateString
        holder.done.isChecked = dataSet[position].done
    }
}