package com.redondo.puydufouexperience.ui.favoritos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.redondo.puydufouexperience.data.EspectaculoDatabase
import com.redondo.puydufouexperience.model.Espectaculo
import com.redondo.puydufouexperience.repository.EspectaculoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repository: EspectaculoRepository

    val listaFavoritos: LiveData<List<Espectaculo>>

    init {
        val dao = EspectaculoDatabase.Companion
            .getDatabase(application)
            .espectaculoDAO()

        repository = EspectaculoRepository(dao)

        listaFavoritos = repository.getFavoritos()
    }

    fun toggleFavorito(espectaculo: Espectaculo) =
        viewModelScope.launch(Dispatchers.IO) {
            espectaculo.esFavorito = !espectaculo.esFavorito
            repository.updateEspectaculo(espectaculo)
        }
}