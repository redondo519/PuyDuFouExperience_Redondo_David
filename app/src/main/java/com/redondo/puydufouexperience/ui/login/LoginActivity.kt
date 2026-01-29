package com.redondo.puydufouexperience.ui.login

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels

import com.redondo.puydufouexperience.databinding.ActivityLoginBinding
import com.redondo.puydufouexperience.sesion.SessionManager
import com.redondo.puydufouexperience.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    //sugerir ultimo usuario
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Rellena usuario si existe
        sessionManager.getUsername()?.let { savedUser ->
            binding.etUser.setText(savedUser)
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPassword.text.toString()
            viewModel.login(user, pass)
        }

        viewModel.loginSuccess.observe(this) { success ->
            if (success) {
                // Guarda el usuario
                sessionManager.saveUsername(binding.etUser.text.toString())

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

