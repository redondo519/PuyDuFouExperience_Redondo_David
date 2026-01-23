package com.redondo.puydufouexperience.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.redondo.puydufouexperience.model.Espectaculo

@Dao
interface EspectaculoDAO {

    @Insert
    suspend fun insert(espectaculo: Espectaculo)

    @Query("SELECT * FROM espectaculos")
    suspend fun getAll(): List<Espectaculo>
}
