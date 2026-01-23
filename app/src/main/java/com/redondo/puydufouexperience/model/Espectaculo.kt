package com.redondo.puydufouexperience.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "espectaculos")
data class Espectaculo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val descripcion: String,
    val imagenResId: Int? = null,
    val horarios: String,
    val duracionMin: Int,
    val zona: String,
    var esFavorito: Boolean = false
)


