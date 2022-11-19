package com.novita.ugd_rumahsakit.api

class DokterApi {
    companion object {
        val BASE_URL = "http://10.53.5.174/pbp/ci4-apiserver/public/"

        val GET_ALL_URL = BASE_URL + "dokter/"
        val GET_BY_ID_URL = BASE_URL + "dokter/"
        val ADD_URL = BASE_URL + "dokter"
        val UPDATE_URL = BASE_URL + "dokter/"
        val DELETE_URL = BASE_URL + "dokter/"
    }
}