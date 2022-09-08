package com.novita.ugd_rumahsakit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.entityDaftarNama.Home
import com.novita.ugd_rumahsakit.entityDaftarNama.Konsultasi


class FragmentKonsultasi : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_konsultasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter: RVKonsultasiAdapter = RVKonsultasiAdapter(Konsultasi.listOfKonsultasi)

        val rvKonsultasi: RecyclerView = view.findViewById(R.id.rv_konsultasi)
        rvKonsultasi.layoutManager = layoutManager
        rvKonsultasi.setHasFixedSize(true)
        rvKonsultasi.adapter = adapter
    }
}
