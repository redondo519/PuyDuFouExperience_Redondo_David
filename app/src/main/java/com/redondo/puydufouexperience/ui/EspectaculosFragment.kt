package com.redondo.puydufouexperience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.FragmentEspectaculosBinding

import androidx.navigation.fragment.findNavController


class EspectaculosFragment : Fragment(R.layout.fragment_espectaculos) {

    private var _binding: FragmentEspectaculosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EspectaculosAdapter


    private val viewModel: EspectaculosViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEspectaculosBinding.bind(view)

        setupRecyclerView()
        observarViewModel()
    }

    private fun setupRecyclerView() {
        adapter = EspectaculosAdapter(emptyList()) { espectaculo ->
            val action =
                EspectaculosFragmentDirections
                    .actionEspectaculosFragmentToEspectaculoDetalleFragment(
                        espectaculo.id
                    )

            findNavController().navigate(action)
        }

        binding.recyclerEspectaculos.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerEspectaculos.adapter = adapter
    }

    private fun observarViewModel() {

        viewModel.listaEspectaculos.observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}