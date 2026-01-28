package com.redondo.puydufouexperience.repository

import com.redondo.puydufouexperience.data.EspectaculoDAO
import com.redondo.puydufouexperience.model.Espectaculo
import kotlinx.coroutines.flow.Flow
import com.redondo.puydufouexperience.R

class EspectaculoRepository(
    private val espectaculoDAO: EspectaculoDAO
) {

    //Obtener lista de espectaculos
    val espectaculos: Flow<List<Espectaculo>> = espectaculoDAO.getAll()

    //Agregar un nuevo espectaculo
    suspend fun agregarEspectaculo(espectaculo: Espectaculo) {
        espectaculoDAO.insert(espectaculo)
    }

    //Obtener todos los espectaculos
    //fun getEspectaculos() = espectaculoDAO.getAll()

    //Obtener un espectaculo sabiendo su id
    suspend fun obtenerEspectaculoPorId(id: Int): Espectaculo? {
        return espectaculoDAO.getById(id)
    }

    //Modificar espectaculo
    suspend fun updateEspectaculo(espectaculo: Espectaculo) {
        espectaculoDAO.updateEspectaculo(espectaculo)
    }

    //Obtener espectaculo marcados como favoritos
    fun getFavoritos() = espectaculoDAO.getFavoritos()


    /**
     * Inicializa Los espectaculos, solo si la lista esta vacia.
     */
    suspend fun inicializarDatos() {
        if(espectaculoDAO.count() == 0){
            espectaculoDAO.insertAll(listaInicial())
        }
        //espectaculoDAO.deleteAll()
    }

    //Devuelve lista de espectaculos
    private fun listaInicial(): List<Espectaculo> {
        return listOf(
            Espectaculo(
                nombre = "Los Fogones",
                descripcion = "Cocina Tradicional de Calidad",
                zona = "Zona Central",
                duracionMin = 30,
                horarios = "22:30 - 00:30",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8560,
                longitud = -4.0230,
                tipo = "restaurante",
                horaInicioMin = 1320
            ),
            Espectaculo(
                nombre = "Restaurante Italiano",
                descripcion = "Comida a tu gusto",
                zona = "El Arrabal",
                duracionMin = 30,
                horarios = "11:30 - 12:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8541,
                longitud = -4.0255,
                tipo = "restaurante",
                horaInicioMin = 660
            ),
            Espectaculo(
                nombre = "Recuerdos Toledo",
                descripcion = "Llevate un buen recuerdo",
                zona = "El Arrabal",
                duracionMin = 1,
                horarios = "11:00 - 14:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8572,
                longitud = -4.0220,
                tipo = "tienda",
                horaInicioMin = 1320
            ),
            Espectaculo(
                nombre = "Tienda Recuerdos",
                descripcion = "Descubre Toledo sobre ruedas",
                zona = "Zona Central",
                duracionMin = 45,
                horarios = "6:30 - 08:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8547,
                longitud = -4.0245,
                tipo = "tienda",
                horaInicioMin = 360
            ),
            Espectaculo(
                nombre = "El Sueño de Toledo",
                descripcion = "Gran espectáculo nocturno...",
                zona = "Zona Central",
                duracionMin = 130,
                horarios = "22:30 - 00:30",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8567,
                longitud = -4.0245,
                tipo = "espectaculo",
                horaInicioMin = 1320
            ),
            Espectaculo(
                nombre = "Espectaculo Toledano",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 40,
                horarios = "11:30 - 12:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8580,
                longitud = -4.0255,
                tipo = "sintipo",
                horaInicioMin = 660
            ),
            Espectaculo(
                nombre = "A Pluma y Espada",
                descripcion = "Duelo de honor y aventuras...",
                zona = "El Arrabal",
                duracionMin = 90,
                horarios = "11:00 - 14:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8569,
                longitud = -4.0249,
                tipo = "espectaculo",
                horaInicioMin = 660
            ),
            Espectaculo(
                nombre = "El Sueño de Toledo II",
                descripcion = "Gran espectáculo al amanecer...",
                zona = "Zona Central",
                duracionMin = 120,
                horarios = "6:30 - 08:00",
                imagenResId = R.drawable.espectaculo_imagen,
                latitud = 39.8569,
                longitud = -4.0225,
                tipo = "espectaculo",
                esFavorito = true,
                horaInicioMin = 360

            )

        )
    }
}

