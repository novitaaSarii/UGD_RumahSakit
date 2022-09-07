package com.novita.ugd_rumahsakit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.entityDaftarNama.Home

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter: RVHomeAdapter = RVHomeAdapter(Home.listOfHome)

        val rvDosen: RecyclerView = view.findViewById(R.id.rv_home)
        rvDosen.layoutManager = layoutManager
        rvDosen.setHasFixedSize(true)
        rvDosen.adapter = adapter
    }
}