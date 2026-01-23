package com.redondo.puydufouexperience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.FragmentEspectaculoDetalleBinding
import com.redondo.puydufouexperience.model.Espectaculo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EspectaculoDetalleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EspectaculoDetalleFragment : Fragment(R.layout.fragment_espectaculo_detalle) {

    private var _binding: FragmentEspectaculoDetalleBinding? = null
    private val binding get() = _binding!!

    private val args: EspectaculoDetalleFragmentArgs by navArgs()
    private val viewModel: EspectaculoDetalleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEspectaculoDetalleBinding.bind(view)

        viewModel.cargarEspectaculo(args.espectaculoId)

        viewModel.espectaculo.observe(viewLifecycleOwner) { espectaculo ->
            mostrarDatos(espectaculo)
        }
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