package com.novita.ugd_rumahsakit.api

class userApi {
    companion object{
        val BASE_URL = "http://192.168.124.230/webapiPBP/rumah_sakit/public/api/"
        val GET_ALL_URL = BASE_URL + "user"
        val GET_BY_ID_URL = BASE_URL + "user/"
        val ADD_URL = BASE_URL + "user"
        val LOGIN_URL = BASE_URL + "login"
        val REGISTER_URL = BASE_URL + "register"
    }
}