package com.novita.ugd_rumahsakit.entityDaftarNama

class Home(var nama: String, var alamat: String, var noTelp: String, var namaDokter: String, var IDDokter: Int ) {
    companion object {
        @JvmField
        var listOfHome = arrayOf(
            Home("Lala", "Jl.Melati", "123", "Dr.Rizky", "0011"),
            Home("Jenny", "Jl.Sudirman", "456", "Dr.Riri", "0022"),
            Home("Riri", "Jl.Kledokan", "678", "Dr.Henri", "0033"),
            Home("Nadin", "Jl.Mawar", "891", "Dr.Tania", "0044"),
            Home("Viera", "Jl.Babarsari", "234", "Dr.Novi", "0055"),
        )
    }
}