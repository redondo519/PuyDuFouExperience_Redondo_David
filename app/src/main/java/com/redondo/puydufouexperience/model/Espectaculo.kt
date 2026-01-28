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
    val horarios: String = "",
    val horaInicioMin: Int, //minuto del dia en que comienza
    val duracionMin: Int, //total de minutos que dura
    val zona: String,
    var esFavorito: Boolean = false,
    val latitud: Double = 39.8567,
    val longitud: Double = -4.0245,
    val tipo: String = "espectaculo" //"restaurante","tienda"

)


