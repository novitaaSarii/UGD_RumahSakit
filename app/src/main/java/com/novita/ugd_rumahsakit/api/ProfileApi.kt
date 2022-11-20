package com.novita.ugd_rumahsakit.api

class ProfileApi {

    companion object {
        val BASE_URL = "http://192.168.43.145/ci4-apiserver/public/ProfileRumahSakit"

        val GET_ALL_URL = BASE_URL + "profile/"
        val GET_BY_USERNAME_URL = BASE_URL + "profile/"
        val ADD_URL = BASE_URL + "profile"
        val UPDATE_URL = BASE_URL + "profile/"

    }
}