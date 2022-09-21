package com.novita.ugd_rumahsakit.MainAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.Task.Task
import com.novita.ugd_rumahsakit.databinding.RecycleviewCreateaccountBinding

class CreateAccountAdapter (val taskList: List<Task>): RecyclerView.Adapter<CreateAccountAdapter.MainViewHolder>() {
    inner class MainViewHolder(val itemBinding: RecycleviewCreateaccountBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(task: Task) {
            itemBinding.itemTitle.text = task.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecycleviewCreateaccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}