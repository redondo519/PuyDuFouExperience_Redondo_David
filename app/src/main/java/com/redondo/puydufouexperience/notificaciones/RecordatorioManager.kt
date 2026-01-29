package com.redondo.puydufouexperience.notificaciones

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.util.DateTimeUtils

object RecordatorioManager {

    @SuppressLint("ScheduleExactAlarm")
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    fun programarRecordatorio(
        context: Context,
        espectaculo: Espectaculo,
        minutosAntes: Int
    ) {

        val prefs = context.getSharedPreferences("ajustes", Context.MODE_PRIVATE)
        val notificacionesActivas = prefs.getBoolean("notificaciones", true)

        if (!notificacionesActivas) return

        val intent = Intent(context, RecordatorioReceiver::class.java).apply {
            putExtra("titulo", espectaculo.nombre)
            putExtra("hora", espectaculo.horaInicioMin)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            espectaculo.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val tiempoEspectaculo =
            DateTimeUtils.horaInicioToMillis(espectaculo.horaInicioMin)

        val tiempoAviso = tiempoEspectaculo - (minutosAntes * 60 * 1000)


        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            tiempoAviso,
            pendingIntent
        )
    }
}
