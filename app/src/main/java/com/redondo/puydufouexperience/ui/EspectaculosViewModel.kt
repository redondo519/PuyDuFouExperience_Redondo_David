package com.redondo.puydufouexperience.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.repository.EspectaculoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EspectaculosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EspectaculoRepository

    private val _listaEspectaculos = MutableLiveData<List<Espectaculo>>()
    val listaEspectaculos: LiveData<List<Espectaculo>> get() = _listaEspectaculos

    init {
        val db = EspectaculoDatabase.getDatabase(application)
        val dao = db.espectaculoDAO()
        repository = EspectaculoRepository(dao)

        cargarEspectaculos()
    }

    fun cargarEspectaculos() {
        viewModelScope.launch(Dispatchers.IO) {
            val espectaculos = repository.obtenerEspectaculos()
            withContext(Dispatchers.Main) {
                _listaEspectaculos.value = espectaculos
            }
        }
    }
}

