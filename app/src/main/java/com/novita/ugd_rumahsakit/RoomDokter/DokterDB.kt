package com.novita.ugd_rumahsakit.RoomDokter

import android.content.Context
import android.view.ContextMenu
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Dokter::class],
    version = 1
)
abstract class DokterDB :RoomDatabase(){
    abstract fun DokterDAO() : DokterDAO

    companion object{
        @Volatile private var instance: DokterDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            DokterDB::class.java,
            "dokter12345.db"
        ).build()
    }
}