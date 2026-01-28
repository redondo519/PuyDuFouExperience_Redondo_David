package com.redondo.puydufouexperience.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.redondo.puydufouexperience.R

class RecordatorioReceiver : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {

        val titulo = intent.getStringExtra("titulo") ?: "Espectáculo"
        val horaMin = intent.getIntExtra("horaMin", 0)

        val hora = horaMin / 60
        val minuto = horaMin % 60

        val horaTexto = String.format("%02d:%02d", hora, minuto)

        val notification = NotificationCompat.Builder(
            context,
            NotificationUtils.CHANNEL_ID
        )
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Recordatorio")
            .setContentText("$titulo empieza a las $horaTexto")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(System.currentTimeMillis().toInt(), notification)
    }
}

