package com.novita.ugd_rumahsakit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [register::class],
    version = 1
)
abstract class registerDB :RoomDatabase(){
    abstract fun registerDAO() : registerDAO

    companion object{
        @Volatile private var instance: registerDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            registerDB::class.java,
            "register12345.db"
        ).build()
    }
}