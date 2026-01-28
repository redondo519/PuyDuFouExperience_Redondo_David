package com.redondo.puydufouexperience.ui


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.FragmentEspectaculoDetalleBinding
import com.redondo.puydufouexperience.model.Espectaculo
import androidx.appcompat.app.AlertDialog



class EspectaculoDetalleFragment : Fragment(R.layout.fragment_espectaculo_detalle) {

    private var _binding: FragmentEspectaculoDetalleBinding? = null
    private val binding get() = _binding!!

    private val args: EspectaculoDetalleFragmentArgs by navArgs()
    private val viewModel: EspectaculoDetalleViewModel by viewModels()

    @SuppressLint("ScheduleExactAlarm")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEspectaculoDetalleBinding.bind(view)

        viewModel.cargarEspectaculo(args.espectaculoId)

        viewModel.espectaculo.observe(viewLifecycleOwner) { espectaculo ->
            mostrarDatos(espectaculo)
        }

        //Observar espectaculo
        viewModel.espectaculo.observe(viewLifecycleOwner) { espectaculo ->
            actualizarIconoFavorito(espectaculo.esFavorito)

            binding.btnFavorito.setOnClickListener {
                viewModel.toggleFavorito(espectaculo)
            }
        }

        //Recordatorios
        val btnRecordatorio = view.findViewById<Button>(R.id.btnRecordatorio)


        btnRecordatorio.setOnClickListener {

            val espectaculo = viewModel.espectaculo.value ?: return@setOnClickListener

            val opciones = arrayOf("5 min", "10 min", "15 min", "30 min")
            val valores = arrayOf(5, 10, 15, 30)

            AlertDialog.Builder(requireContext())
                .setTitle("Avisar antes de")
                .setItems(opciones) { _, which ->
                    RecordatorioManager.programarRecordatorio(
                        requireContext(),
                        espectaculo,
                        valores[which]
                    )

                    Toast.makeText(
                        requireContext(),
                        "Recordatorio creado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }



    }


    //actualizar icono favorito
    private fun actualizarIconoFavorito(esFavorito: Boolean) {
        val icono = if (esFavorito) {
            R.drawable.ic_favorite_filled //relleno
        } else {
            R.drawable.ic_favorite_border //solo borde
        }
        binding.btnFavorito.setImageResource(icono)
    }




    private fun mostrarDatos(espectaculo: Espectaculo) {
        binding.imgDetalle.setImageResource(espectaculo.imagenResId ?: R.drawable.espectaculo_imagen)
        binding.txtNombre.text = espectaculo.nombre
        binding.txtZona.text = espectaculo.zona
        binding.txtDuracion.text = "${espectaculo.duracionMin} min"
        binding.txtHorarios.text = "Horarios: ${espectaculo.horarios}"
        binding.txtDescripcion.text = espectaculo.descripcion
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}