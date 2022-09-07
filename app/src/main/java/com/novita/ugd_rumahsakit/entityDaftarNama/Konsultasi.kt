package com.novita.ugd_rumahsakit.entityDaftarNama

class Konsultasi(var namaDokter: String, var spesialis: String, kontakDokter: String, var IDDokter: Int) {

    companion object {
        @JvmStatic
        var listOfKonsultasi = arrayOf(
            Konsultasi("Dr.Rizky", "Saraf", "01234", "0011"),
            Konsultasi("Dr.Riri", "Umum", "45678", "0022"),
            Konsultasi("Dr.Henri", "Umum", "45679", "0033"),
            Konsultasi("Dr.Tania", "Umum", "45671", "0044"),
            Konsultasi("Dr.Novi", "Anak", "12345", "0055"),
        )
    }
}

