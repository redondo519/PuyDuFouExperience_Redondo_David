package com.redondo.puydufouexperience.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.redondo.puydufouexperience.R

@Database(
    entities = [Espectaculo::class],
    version = 3,
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
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3) //Usar migraciones
                    .build()

                INSTANCE = instance
                instance
            }
        }

        //Migrar de  version problema con room al cambiar estructura BD
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {

                db.execSQL(
                    """
            ALTER TABLE espectaculos
            ADD COLUMN latitud REAL NOT NULL DEFAULT 39.8567
            """.trimIndent()
                )

                db.execSQL(
                    """
            ALTER TABLE espectaculos
            ADD COLUMN longitud REAL NOT NULL DEFAULT -4.0245
            """.trimIndent()
                )
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
            ALTER TABLE espectaculos
            ADD COLUMN tipo TEXT NOT NULL DEFAULT 'espectaculo'
            """.trimIndent()
                )
            }
        }


    }


}
