package com.novita.ugd_rumahsakit

class Home(var name: String, var nomorPasien: Int) {
    companion object {
        @JvmField
        var listOfHome = arrayOf(
            Home("Lala", "0011"),
            Home("Jenny", "0022"),
            Home("Riri", "0033"),
            Home("Nadin", "0044"),
            Home("Viera", "0055"),
            Home("Tulus", "0066"),
            Home("Angel", "0077"),
        )
    }
}