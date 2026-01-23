package com.redondo.puydufouexperience.model


data class Espectaculo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int,
    val horarios: List<String>,
    val duracionMin: Int,
    val zona: String,
    val esFavorito: Boolean = false
)

