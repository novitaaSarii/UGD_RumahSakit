package com.novita.ugd_rumahsakit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.entityDaftarNama.Home
import com.novita.ugd_rumahsakit.entityDaftarNama.Konsultasi

class RVKonsultasiAdapter (private val data: Array<Konsultasi>) : RecyclerView.Adapter<RVKonsultasiAdapter.viewHolder>(){
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVKonsultasiAdapter.viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(  R.layout.rv_konsultasi, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVKonsultasiAdapter.viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNama.text = currentItem.namaDokter
        holder.tvDetails.text = "${currentItem.IDDokter} - ${currentItem.spesialis} - ${currentItem.kontakDokter} ${currentItem.IDDokter}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvNama : TextView = itemView.findViewById(R.id.tv_nama)
        val tvDetails : TextView = itemView.findViewById(R.id.tv_details)
    }
}