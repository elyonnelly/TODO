package com.example.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ListItemModel
import com.example.todo.R
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class TodoListAdapter(private val dataSet: List<ListItemModel>,
                      private val clickListener: OnEditClickListener,
                      private val changeTaskStatusListener : OnChangeTaskStatusListener) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val title : TextView = view.findViewById(R.id.title)
        val description : TextView = view.findViewById(R.id.description)
        val date : TextView = view.findViewById(R.id.date)
        val time : TextView = view.findViewById(R.id.time)
        val done : CheckBox = view.findViewById(R.id.done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.description.text = dataSet[position].description

        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        holder.date.text = dataSet[position].date.format(formatter)

        holder.time.text = dataSet[position].time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

        holder.done.isChecked = dataSet[position].done
        
        holder.done.setOnCheckedChangeListener {
            _: CompoundButton, b: Boolean -> changeTaskStatusListener.onChangeTaskStatus(dataSet[position].id, b)
        }
        holder.view.setOnClickListener {
            clickListener.onClick(dataSet[position].id)
        }
    }
}