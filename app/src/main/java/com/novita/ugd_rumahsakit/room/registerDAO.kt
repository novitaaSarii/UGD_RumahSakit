package com.novita.ugd_rumahsakit.room

import androidx.room.*

@Dao
interface registerDAO {
    @Insert
    suspend fun addregister(register: register)

    @Update
    suspend fun updateregister(register: register)

    @Delete
    suspend fun deleteregister(register: register)

    @Query("SELECT * FROM register")  /*mencari semua username */
    suspend fun getregister() : List<register>

    @Query("SELECT * FROM register WHERE username =:username")/*mencari 1 username spesifik*/
    suspend fun getregister(username:String) : List<register>

}