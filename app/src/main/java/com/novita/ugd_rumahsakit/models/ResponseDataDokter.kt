package com.novita.ugd_rumahsakit.models

import com.google.gson.annotations.SerializedName

data class ResponseDataDokter(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<dokter>
)

