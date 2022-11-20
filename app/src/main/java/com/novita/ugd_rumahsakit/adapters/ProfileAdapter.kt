package com.novita.ugd_rumahsakit.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.novita.ugd_rumahsakit.ProfilActivity
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.models.profile
import com.novita.ugd_rumahsakit.profile.AddEditActivityProfile
import com.novita.ugd_rumahsakit.profile.ProfileActivity

import java.util.*
import kotlin.collections.ArrayList

class ProfileAdapter (private var profileList : List<profile>, context: Context) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>(), Filterable {

    private var filteredprofileList: MutableList<profile>
    private val context: Context

    init{
        filteredprofileList = ArrayList(profileList)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredprofileList.size
    }

    fun setprofileList(profileList: Array<profile>) {
        this.profileList = profileList.toList()
        filteredprofileList = profileList.toMutableList()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = filteredprofileList[position]
        holder.tvusername.text = profile.username
        holder.tvpassword.text = profile.password
        holder.tvemail.text = profile.email
        holder.tvtanggal.text = profile.tanggal
        holder.tvnomor.text = profile.nomor

        holder.cvprofile.setOnClickListener {
            val i = Intent(context, AddEditActivityProfile::class.java)
            i.putExtra("username", profile.username)
            if(context is ProfilActivity)
                context.startActivityForResult(i, ProfileActivity.LAUNCH_ADD_ACTIVITY)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                var charSequenceString = charSequence.toString()
                val filtered: MutableList<profile> = java.util.ArrayList()
                if(charSequenceString.isEmpty()){
                    filtered.addAll(profileList)
                }else{
                    for(profile in profileList){
                        if(profile.username.lowercase(Locale.getDefault())
                                .contains(charSequenceString.lowercase(Locale.getDefault()))
                        ) filtered.add(profile)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filtered
                return  filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredprofileList.clear()
                filteredprofileList.addAll((filterResults.values as List<profile>))
                notifyDataSetChanged()
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvusername: TextView
        var tvpassword: TextView
        var tvemail: TextView
        var tvtanggal: TextView
        var tvnomor: TextView
        var cvprofile: CardView

        init {
            tvusername = itemView.findViewById(R.id.tv_username)
            tvpassword = itemView.findViewById(R.id.tv_password)
            tvemail = itemView.findViewById(R.id.tv_email)
            tvtanggal = itemView.findViewById(R.id.tv_tanggal)
            tvnomor = itemView.findViewById(R.id.tv_nomor)
            cvprofile = itemView.findViewById(R.id.cv_profile)
        }
    }
}