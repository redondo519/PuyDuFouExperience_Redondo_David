package com.redondo.puydufouexperience.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    //Control de contraseña
    fun login(user: String, password: String) {

        _loginSuccess.value = (user == "demo" && password == "demo")
    }
}

