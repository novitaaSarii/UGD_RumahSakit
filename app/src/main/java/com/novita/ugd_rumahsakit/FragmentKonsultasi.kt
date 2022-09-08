package com.novita.ugd_rumahsakit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novita.ugd_rumahsakit.entityDaftarNama.Konsultasi

class FragmentDosen : Fragment() {
    //ini masih belum pasti sih coba di cek lagi ya//
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_konsultasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter: RVKonsultasi= RVKonsultasiAdapter(Konsultasi.listOfKonsultasi)

        val rvDosen: RecyclerView = view.findViewById(R.id.rv_konsultasi)
        rvDosen.layoutManager = layoutManager
        rvDosen.setHasFixedSize(true)
        rvDosen.adapter = adapter
    }
}