package com.redondo.puydufouexperience.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redondo.puydufouexperience.model.Espectaculo

@Database(
    entities = [Espectaculo::class],
    version = 1,
    exportSchema = false
)
abstract class EspectaculoDatabase : RoomDatabase() {

    abstract fun espectaculoDAO(): EspectaculoDAO

    companion object {
        @Volatile
        private var INSTANCE: EspectaculoDatabase? = null

        fun getDatabase(context: Context): EspectaculoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EspectaculoDatabase::class.java,
                    "parque_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
