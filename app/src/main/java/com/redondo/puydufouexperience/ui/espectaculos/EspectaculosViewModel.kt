package com.redondo.puydufouexperience.ui.espectaculos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.repository.EspectaculoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EspectaculosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EspectaculoRepository

    val listaEspectaculos: LiveData<List<Espectaculo>>

    init {
        val dao = EspectaculoDatabase
            .getDatabase(application)
            .espectaculoDAO()

        repository = EspectaculoRepository(dao)

        listaEspectaculos = repository.espectaculos.asLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            repository.inicializarDatos()
        }
    }

    /*
    fun agregarEspectaculo(espectaculo: Espectaculo) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.agregarEspectaculo(espectaculo)
        }

     */
}


