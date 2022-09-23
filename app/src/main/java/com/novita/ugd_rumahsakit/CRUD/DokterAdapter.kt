package com.novita.ugd_rumahsakit.CRUD

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.RoomDokter.Dokter
import kotlinx.android.synthetic.main.activity_createraccount.view.*
import kotlinx.android.synthetic.main.activity_dokter_adapter.view.*

class DokterAdapter (private val Dokter: ArrayList<Dokter>, private val listener: OnAdapterListerner):
    RecyclerView.Adapter<DokterAdapter.DokterViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DokterAdapter.DokterViewHolder {
        return DokterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_createraccount, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DokterViewHolder, position: Int) {
        val Dokter = Dokter[position]
        holder.view.btn_create.setOnClickListener {
            listener.onCreate(Dokter)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(Dokter)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(Dokter)
        }
    }

    override fun getItemCount() = Dokter.size

    inner class DokterViewHolder( val view: View) :
        RecyclerView.ViewHolder(view)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Dokter>){
        Dokter.clear()
        Dokter.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(dokter: Dokter)
        fun onUpdate(dokter: Dokter)
        fun onDelete(dokter: Dokter)
    }
}