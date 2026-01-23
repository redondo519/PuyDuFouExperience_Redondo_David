package com.redondo.puydufouexperience.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.model.Espectaculo

class EspectaculosViewModel : ViewModel() {

    private val _listaEspectaculos = MutableLiveData<List<Espectaculo>>()
    val listaEspectaculos: LiveData<List<Espectaculo>>
        get() = _listaEspectaculos

    fun setEspectaculos(espectaculos: List<Espectaculo>) {
        _listaEspectaculos.value = espectaculos
    }
}
