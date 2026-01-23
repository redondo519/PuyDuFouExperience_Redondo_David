package com.redondo.puydufouexperience.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.flow.Flow
@Dao
interface EspectaculoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(espectaculo: Espectaculo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(espectaculos: List<Espectaculo>)

    @Query("SELECT * FROM espectaculos ORDER BY id DESC")
    fun getAll(): Flow<List<Espectaculo>>

    @Query("SELECT * FROM espectaculos WHERE id = :id")
    suspend fun getById(id: Int): Espectaculo?

    @Query("SELECT COUNT(*) FROM espectaculos")
    suspend fun count(): Int


    @Query("DELETE FROM espectaculos")
    suspend fun deleteAll()

}
