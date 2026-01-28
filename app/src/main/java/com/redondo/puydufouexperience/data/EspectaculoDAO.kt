package com.redondo.puydufouexperience.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.flow.Flow

@Dao
interface EspectaculoDAO {

    //Insertar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(espectaculo: Espectaculo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(espectaculos: List<Espectaculo>)

    //Obtener todos
    @Query("SELECT * FROM espectaculos ORDER BY id DESC")
    fun getAll(): Flow<List<Espectaculo>>

    //Seleccionar por id
    @Query("SELECT * FROM espectaculos WHERE id = :id")
    suspend fun getById(id: Int): Espectaculo?

    //Modificar
    @Update
    suspend fun updateEspectaculo(espectaculo: Espectaculo)

    //Eliminar
    @Query("DELETE FROM espectaculos")
    suspend fun deleteAll()

    //Contar para saber si hay espectaculos
    @Query("SELECT COUNT(*) FROM espectaculos")
    suspend fun count(): Int

    //Obtener los espectaculos marcados como Favorito
    @Query("SELECT * FROM espectaculos WHERE esFavorito = 1")
    fun getFavoritos(): LiveData<List<Espectaculo>>

}
