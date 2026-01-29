package com.redondo.puydufouexperience.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.FragmentAjustesBinding
import java.util.Locale


class AjustesFragment : Fragment() {

    private var _binding: FragmentAjustesBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = requireContext().getSharedPreferences("ajustes", Context.MODE_PRIVATE)


        // Cargar valores guardados
        when (prefs.getString("idioma", "es")) {
            "es" -> binding.rbEspanol.isChecked = true
            "en" -> binding.rbIngles.isChecked = true
        }

        binding.switchNotificaciones.isChecked = prefs.getBoolean("notificaciones", true)

        when (prefs.getString("tema", "claro")) {
            "claro" -> binding.rbClaro.isChecked = true
            "oscuro" -> binding.rbOscuro.isChecked = true
        }

        // ------------------------------
        // Listeners
        // ------------------------------

        // Idioma
        binding.rgIdioma.setOnCheckedChangeListener { _, checkedId ->
            val idioma = if (checkedId == R.id.rbEspanol) "es" else "en"
            guardarIdioma(idioma)
        }

        // Notificaciones
        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
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
        binding.rgTema.setOnCheckedChangeListener { _, checkedId ->
            val modo = if (checkedId == R.id.rbClaro)
                AppCompatDelegate.MODE_NIGHT_NO
            else
                AppCompatDelegate.MODE_NIGHT_YES

            prefs.edit()
                .putString("tema", if (checkedId == R.id.rbClaro) "claro" else "oscuro")
                .apply()

            AppCompatDelegate.setDefaultNightMode(modo)
        }

        // Logout
        binding.rbLogout.setOnClickListener {
            val sessionManager = SessionManager(requireContext())
            sessionManager.clearSession()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    // Guardar idioma
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

    //Destruir vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

