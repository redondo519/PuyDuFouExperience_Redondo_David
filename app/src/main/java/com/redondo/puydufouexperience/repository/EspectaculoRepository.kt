package com.redondo.puydufouexperience.repository

import com.redondo.puydufouexperience.data.EspectaculoDAO
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.flow.Flow
import com.redondo.puydufouexperience.R

class EspectaculoRepository(
    private val espectaculoDAO: EspectaculoDAO
) {

    val espectaculos: Flow<List<Espectaculo>> = espectaculoDAO.getAll()

    suspend fun agregarEspectaculo(espectaculo: Espectaculo) {
        espectaculoDAO.insert(espectaculo)
    }

    suspend fun obtenerEspectaculoPorId(id: Int): Espectaculo? {
        return espectaculoDAO.getById(id)
    }

    suspend fun inicializarDatos() {
        espectaculoDAO.deleteAll()
        espectaculoDAO.insertAll(listaInicial())
    }


    private fun listaInicial(): List<Espectaculo> {
        return listOf(
            Espectaculo(
                nombre = "El Sueño de Toledo",
                descripcion = "Gran espectáculo nocturno...",
                zona = "Zona Central",
                duracionMin = 120,
                horarios = "22:30 - 00:30",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "A Pluma y Espada Vrs Corta",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 30,
                horarios = "11:30 - 12:00",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "A Pluma y Espada",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 120,
                horarios = "12:00 - 14:00",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "El Sueño de Toledo II",
                descripcion = "Gran espectáculo al amanecer...",
                zona = "Zona Central",
                duracionMin = 90,
                horarios = "6:30 - 08:00",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "El Sueño de Toledo",
                descripcion = "Gran espectáculo nocturno...",
                zona = "Zona Central",
                duracionMin = 120,
                horarios = "22:30 - 00:30",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "A Pluma y Espada Vrs Corta",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 30,
                horarios = "11:30 - 12:00",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "A Pluma y Espada",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 120,
                horarios = "12:00 - 14:00",
                imagenResId = R.drawable.espectaculo_imagen
            ),
            Espectaculo(
                nombre = "El Sueño de Toledo II",
                descripcion = "Gran espectáculo al amanecer...",
                zona = "Zona Central",
                duracionMin = 90,
                horarios = "6:30 - 08:00",
                imagenResId = R.drawable.espectaculo_imagen
            )
        )
    }
}

