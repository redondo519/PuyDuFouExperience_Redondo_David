package com.redondo.puydufouexperience.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.redondo.puydufouexperience.R

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
                    "espectaculos_db"
                )
                    //.addCallback(DatabaseCallback(context))
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
/*
    private class DatabaseCallback(
        private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                val database = getDatabase(context)
                val dao = database.espectaculoDAO()

                dao.insertAll(listaInicial())
            }
        }

        private fun listaInicial(): List<Espectaculo> {
            return listOf(
                Espectaculo(
                    nombre = "El Sueño de Toledo 1",
                    descripcion = "Gran espectáculo nocturno...",
                    zona = "Zona Central",
                    duracionMin = 80,
                    horarios = "22:30 - 00:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "A Pluma y Espada 1",
                    descripcion = "Duelo de honor y aventuras...",
                    zona = "El Arrabal",
                    duracionMin = 30,
                    horarios = "12:00 - 16:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "El Sueño de Toledo 2",
                    descripcion = "Gran espectáculo nocturno...",
                    zona = "Zona Central",
                    duracionMin = 70,
                    horarios = "22:30 - 00:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "A Pluma y Espada 2",
                    descripcion = "Duelo de honor y aventuras...",
                    zona = "El Arrabal",
                    duracionMin = 30,
                    horarios = "12:00 - 16:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "El Sueño de Toledo",
                    descripcion = "Gran espectáculo nocturno...",
                    zona = "Zona Central",
                    duracionMin = 70,
                    horarios = "22:30 - 00:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "A Pluma y Espada",
                    descripcion = "Duelo de honor y aventuras...",
                    zona = "El Arrabal",
                    duracionMin = 30,
                    horarios = "12:00 - 16:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "El Sueño de Toledo",
                    descripcion = "Gran espectáculo nocturno...",
                    zona = "Zona Central",
                    duracionMin = 70,
                    horarios = "22:30 - 00:00",
                    imagenResId = R.drawable.espectaculo_imagen
                ),
                Espectaculo(
                    nombre = "A Pluma y Espada",
                    descripcion = "Duelo de honor y aventuras...",
                    zona = "El Arrabal",
                    duracionMin = 30,
                    horarios = "12:00 - 16:00",
                    imagenResId = R.drawable.espectaculo_imagen
                )
            )
        }
    }

 */

}
