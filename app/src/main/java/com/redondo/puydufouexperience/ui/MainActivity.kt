package com.redondo.puydufouexperience.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.ActivityMainBinding

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Idioma
    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("ajustes", Context.MODE_PRIVATE)
        val idioma = prefs.getString("idioma", "es") ?: "es"

        val locale = Locale.forLanguageTag(idioma)
        Locale.setDefault(locale)

        val config = newBase.resources.configuration
        config.setLocale(locale)

        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)


        //Preferencias
        val prefs = getSharedPreferences("ajustes", Context.MODE_PRIVATE)

        // Tema
        when (prefs.getString("tema", "claro")) {
            "oscuro" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        //Notificaciones
        NotificationUtils.crearCanal(this)


    }

}