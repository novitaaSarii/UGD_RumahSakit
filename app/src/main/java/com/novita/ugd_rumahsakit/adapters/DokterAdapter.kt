package com.novita.ugd_rumahsakit.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.novita.ugd_rumahsakit.spesialisnamaDokter.Add_edit_Activity
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.spesialisnamaDokter.DokterHome
import com.novita.ugd_rumahsakit.models.dokter
import java.util.*
import kotlin.collections.ArrayList

class DokterAdapter (private var dokterList : List<dokter>, context: Context) :
    RecyclerView.Adapter<DokterAdapter.ViewHolder>(), Filterable {

    private var filtereddokterList: MutableList<dokter>
    private val context: Context

    init{
        filtereddokterList = ArrayList(dokterList)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dokter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filtereddokterList.size
    }

    fun setdokterList(dokterList: Array<dokter>) {
        this.dokterList = dokterList.toList()
        filtereddokterList = dokterList.toMutableList()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dokter = filtereddokterList[position]
        holder.tviddokter.text = dokter.dokterid
        holder.tvnama.text = dokter.dokternama
        holder.tvspesialis.text = dokter.dokterspesialis
        holder.tvalamat.text = dokter.dokteralamat

        holder.btnDelete.setOnClickListener {
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
            materialAlertDialogBuilder.setTitle("Konfirmasi")
                .setMessage("apakah anda yakin ingin menghapus data mahasiswa ini?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { _, _ ->
                    if(context is DokterHome) dokter.id?.let { it1 ->
                        context.deleteDokter(
                            it1
                        )
                    }
                }
                .show()
        }
        holder.cvdokter.setOnClickListener {
            val i = Intent(context, Add_edit_Activity::class.java)
            i.putExtra("id", dokter.id)
            if(context is DokterHome)
                context.startActivityForResult(i, DokterHome.LAUNCH_ADD_ACTIVITY)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                var charSequenceString = charSequence.toString()
                val filtered: MutableList<dokter> = java.util.ArrayList()
                if(charSequenceString.isEmpty()){
                    filtered.addAll(dokterList)
                }else{
                    for(dokter in dokterList){
                        if(dokter.dokternama.lowercase(Locale.getDefault())
                                .contains(charSequenceString.lowercase(Locale.getDefault()))
                        ) filtered.add(dokter)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filtered
                return  filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filtereddokterList.clear()
                filtereddokterList.addAll((filterResults.values as List<dokter>))
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tviddokter: TextView
        var tvnama: TextView
        var tvspesialis: TextView
        var tvalamat: TextView
        var btnDelete: ImageButton
        var cvdokter: CardView

        init {
            tviddokter = itemView.findViewById(R.id.tv_iddokter)
            tvnama = itemView.findViewById(R.id.tv_nama)
            tvspesialis = itemView.findViewById(R.id.tv_spesialis)
            tvalamat = itemView.findViewById(R.id.tv_alamat)
            btnDelete = itemView.findViewById(R.id.btn_delete)
            cvdokter = itemView.findViewById(R.id.cv_dokter)
        }
    }
}