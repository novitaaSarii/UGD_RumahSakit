package com.novita.ugd_rumahsakit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.entityDaftarNama.Home

class RVHomeAdapter (private val data: Array<Home>) : RecyclerView.Adapter<RVHomeAdapter.viewHolder>(){
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHomeAdapter.viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(  R.layout.rv_home, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVHomeAdapter.viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNama.text = currentItem.nama
        holder.tvDetails.text = "${currentItem.alamat} - ${currentItem.noTelp} - ${currentItem.namaDokter} ${currentItem.IDDokter}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvNama : TextView = itemView.findViewById(R.id.tv_nama)
        val tvDetails : TextView = itemView.findViewById(R.id.tv_details)
    }
}