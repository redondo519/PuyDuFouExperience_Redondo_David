package com.redondo.puydufouexperience.ui.mapa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.repository.EspectaculoRepository

class MapaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EspectaculoRepository

    val espectaculos: LiveData<List<Espectaculo>>

    init {
        val dao = EspectaculoDatabase.Companion
            .getDatabase(application)
            .espectaculoDAO()

        repository = EspectaculoRepository(dao)

        espectaculos = repository.espectaculos.asLiveData()
    }
}