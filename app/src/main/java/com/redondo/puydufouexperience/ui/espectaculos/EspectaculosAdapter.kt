package com.redondo.puydufouexperience.ui.espectaculos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redondo.puydufouexperience.databinding.ItemEspectaculoBinding
import com.redondo.puydufouexperience.model.Espectaculo

class EspectaculosAdapter(
    private var listaEspectaculos: List<Espectaculo>,
    private val onItemClick: (Espectaculo) -> Unit
) : RecyclerView.Adapter<EspectaculosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspectaculosViewHolder {
        val binding = ItemEspectaculoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EspectaculosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EspectaculosViewHolder, position: Int) {
        holder.bind(listaEspectaculos[position], onItemClick)
    }

    override fun getItemCount(): Int = listaEspectaculos.size

    fun actualizarLista(nuevaLista: List<Espectaculo>) {
        listaEspectaculos = nuevaLista
        notifyDataSetChanged()
    }
}