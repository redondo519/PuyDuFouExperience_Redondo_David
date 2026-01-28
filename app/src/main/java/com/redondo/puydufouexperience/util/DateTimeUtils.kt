package com.redondo.puydufouexperience.util

import java.util.Calendar

object DateTimeUtils {

    fun horaInicioToMillis(horaInicioMin: Int): Long {
        val calendar = Calendar.getInstance()

        val hora = horaInicioMin / 60
        val minuto = horaInicioMin % 60

        calendar.set(Calendar.HOUR_OF_DAY, hora)
        calendar.set(Calendar.MINUTE, minuto)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // Si ya pasó hoy → mañana
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return calendar.timeInMillis
    }
}
