package com.novita.ugd_rumahsakit

class Konsultasi(var name: String, var jenisKonsultasi: String) {

    companion object {
        @JvmStatic
        var listOfKonsultasi = arrayOf(
            Konsultasi("Dr.Rizky", "Konsultasi Online"),
            Konsultasi("Dr.Henri", "Konsultasi Offline"),
            Konsultasi("Dr.Novi", "Konsultasi Online"),
        )
    }
}