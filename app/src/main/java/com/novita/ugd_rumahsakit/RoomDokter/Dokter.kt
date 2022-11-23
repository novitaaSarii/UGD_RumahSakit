package com.novita.ugd_rumahsakit.RoomDokter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dokter(
    @PrimaryKey(autoGenerate = true )
    val id : Int,
    val namaDokter: String,
    val IDDokter: String,
    val spesialis: String,
    val kontakDokter: String
)
