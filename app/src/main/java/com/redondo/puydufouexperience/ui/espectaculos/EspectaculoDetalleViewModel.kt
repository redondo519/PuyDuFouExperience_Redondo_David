package com.redondo.puydufouexperience.ui.espectaculos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.repository.EspectaculoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EspectaculoDetalleViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository: EspectaculoRepository

    private val _espectaculo = MutableLiveData<Espectaculo>()
    val espectaculo: LiveData<Espectaculo> get() = _espectaculo

    init {
        val db = EspectaculoDatabase.getDatabase(application)
        val dao = db.espectaculoDAO()
        repository = EspectaculoRepository(dao)
    }

    fun cargarEspectaculo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado = repository.obtenerEspectaculoPorId(id)
            withContext(Dispatchers.Main) {
                resultado?.let {
                    _espectaculo.value = it
                }
            }
        }
    }

    //cambia el estado de la variable esFavorito
    fun toggleFavorito(espectaculo: Espectaculo) {
        viewModelScope.launch {
            espectaculo.esFavorito = !espectaculo.esFavorito
            repository.updateEspectaculo(espectaculo)
        }
    }
}

