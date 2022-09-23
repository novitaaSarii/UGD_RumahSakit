package com.novita.ugd_rumahsakit.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class register (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val username: String,
    val password: String,
    val email: String,
    val tanggal: String,
    val nomor: String
)
