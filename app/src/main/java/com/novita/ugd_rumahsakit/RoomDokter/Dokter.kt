package com.novita.ugd_rumahsakit.RoomDokter

import androidx.room.Entity

@Entity
data class Dokter (
    @PrimaryKey(autoGenerate = true)
    val namaDokter: String,
    val IDDokter: String,
    val spesialis: String,
    val kontakDokter: Int
)
