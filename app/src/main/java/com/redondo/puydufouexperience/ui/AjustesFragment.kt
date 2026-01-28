package com.redondo.puydufouexperience.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.redondo.puydufouexperience.R
import java.util.Locale


class AjustesFragment : Fragment(R.layout.fragment_ajustes) {

    private lateinit var prefs: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = requireContext().getSharedPreferences("ajustes", Context.MODE_PRIVATE)

        val rgIdioma = view.findViewById<RadioGroup>(R.id.rgIdioma)
        val rbEspanol = view.findViewById<RadioButton>(R.id.rbEspanol)
        val rbIngles = view.findViewById<RadioButton>(R.id.rbIngles)

        val switchNotificaciones = view.findViewById<SwitchMaterial>(R.id.switchNotificaciones)

        val rgTema = view.findViewById<RadioGroup>(R.id.rgTema)
        val rbClaro = view.findViewById<RadioButton>(R.id.rbClaro)
        val rbOscuro = view.findViewById<RadioButton>(R.id.rbOscuro)

        // Cargar valores guardados
        when (prefs.getString("idioma", "es")) {
            "es" -> rbEspanol.isChecked = true
            "en" -> rbIngles.isChecked = true
        }

        switchNotificaciones.isChecked = prefs.getBoolean("notificaciones", true)

        when (prefs.getString("tema", "claro")) {
            "claro" -> rbClaro.isChecked = true
            "oscuro" -> rbOscuro.isChecked = true
        }

        // Idioma
        rgIdioma.setOnCheckedChangeListener { _, checkedId ->
            val idioma = if (checkedId == R.id.rbEspanol) "es" else "en"
            guardarIdioma(idioma)
        }

        // Notificaciones
        switchNotificaciones.setOnCheckedChangeListener(null)
        switchNotificaciones.isChecked = prefs.getBoolean("notificaciones", true)
        switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notificaciones", isChecked).apply()

            Toast.makeText(
                requireContext(),
                if (isChecked)
                    R.string.notificaciones_activadas
                else
                    R.string.notificaciones_desactivadas,
                Toast.LENGTH_SHORT
            ).show()
        }



        // Tema
        rgTema.setOnCheckedChangeListener { _, checkedId ->
            val tema = if (checkedId == R.id.rbClaro) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }

            prefs.edit()
                .putString("tema", if (checkedId == R.id.rbClaro) "claro" else "oscuro")
                .apply()

            AppCompatDelegate.setDefaultNightMode(tema)
        }
    }

    private fun guardarIdioma(codigo: String) {
        prefs.edit().putString("idioma", codigo).apply()

        val locale = Locale.forLanguageTag(codigo)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)

        requireActivity().apply {
            createConfigurationContext(config)
            recreate()
        }
    }

}
