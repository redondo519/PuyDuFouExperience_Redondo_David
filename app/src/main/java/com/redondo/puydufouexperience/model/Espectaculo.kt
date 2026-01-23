package com.redondo.puydufouexperience.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "espectaculos")
data class Espectaculo(
    @PrimaryKey
    val id: Int,

    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val horarios: String,
    val duracionMin: Int,
    val zona: String,
    val esFavorito: Boolean = false
)

