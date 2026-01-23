package com.redondo.puydufouexperience.repository

import com.redondo.puydufouexperience.data.EspectaculoDAO
import com.redondo.puydufouexperience.model.Espectaculo

class EspectaculoRepository(
    private val espectaculoDAO: EspectaculoDAO
) {

    suspend fun agregarEspectaculo(espectaculo: Espectaculo) {
        espectaculoDAO.insert(espectaculo)
    }

    suspend fun obtenerEspectaculos(): List<Espectaculo> {
        return espectaculoDAO.getAll()
    }
}
