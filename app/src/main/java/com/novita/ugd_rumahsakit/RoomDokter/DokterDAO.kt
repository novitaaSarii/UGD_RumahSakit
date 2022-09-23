package com.novita.ugd_rumahsakit.RoomDokter

import androidx.room.*

@Dao
interface DokterDAO {
    @Insert
    suspend fun addDokter(dokter: Dokter)
    @Update
    suspend fun updateDokter(dokter: Dokter)
    @Delete
    suspend fun deleteDokter(dokter: Dokter)
    @Query("SELECT * FROM dokter" )
    suspend fun getDokter() : List<Dokter>
    @Query("SELECT * FROM dokter  WHERE namaDokter =:namaDokter")
    suspend fun getDokter(namaDokter: String) : List<Dokter>

}