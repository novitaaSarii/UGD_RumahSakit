package com.novita.ugd_rumahsakit.MVVM

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.room.register
import kotlinx.android.synthetic.main.activity_createraccount.view.*

class registerAdapter (private val register: ArrayList<register>, private val listener: OnAdapterListerner):
    RecyclerView.Adapter<registerAdapter.registerViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): registerAdapter.registerViewHolder {
        return registerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_createraccount, parent, false)
        )
    }

    override fun onBindViewHolder(holder: registerAdapter.registerViewHolder, position: Int) {
        val register = register[position]
        holder.view.btn_create.setOnClickListener{
            listener.onCreate(register)
        }
    }

    override fun getItemCount() = register.size

    inner class registerViewHolder(val view: View):
        RecyclerView.ViewHolder(view)

    interface OnAdapterListerner{
        fun onCreate(register: register)
    }
}