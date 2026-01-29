package com.redondo.puydufouexperience.ui.espectaculos

import androidx.recyclerview.widget.RecyclerView
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.ItemEspectaculoBinding
import com.redondo.puydufouexperience.model.Espectaculo

class EspectaculosViewHolder(
    private val binding: ItemEspectaculoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        espectaculo: Espectaculo,
        onItemClick: (Espectaculo) -> Unit
    ) {
        binding.Nombre.text = espectaculo.nombre
        binding.Zona.text = espectaculo.zona
        binding.Duracion.text = "${espectaculo.duracionMin} min"
        binding.imgEspectaculo.setImageResource(espectaculo.imagenResId ?: R.drawable.espectaculo_imagen)

        binding.root.setOnClickListener {
            onItemClick(espectaculo)
        }
    }
}