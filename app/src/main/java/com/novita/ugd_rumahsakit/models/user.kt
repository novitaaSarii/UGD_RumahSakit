package com.novita.ugd_rumahsakit.models

import androidx.room.PrimaryKey

class user(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val tanggal: String,
    val nomor: String
)
