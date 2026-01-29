package com.redondo.puydufouexperience.sesion

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUsername(user: String) {
        prefs.edit()
            .putString("username", user)
            .apply()
    }

    fun getUsername(): String? {
        return prefs.getString("username", null)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}