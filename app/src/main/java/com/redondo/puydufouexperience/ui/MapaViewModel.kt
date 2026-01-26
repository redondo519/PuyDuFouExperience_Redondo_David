package com.redondo.puydufouexperience.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.redondo.puydufouexperience.repository.EspectaculoRepository
import androidx.lifecycle.asLiveData
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo

class MapaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EspectaculoRepository

    val espectaculos: LiveData<List<Espectaculo>>

    init {
        val dao = EspectaculoDatabase
            .getDatabase(application)
            .espectaculoDAO()

        repository = EspectaculoRepository(dao)

        espectaculos = repository.espectaculos.asLiveData()
    }
}

