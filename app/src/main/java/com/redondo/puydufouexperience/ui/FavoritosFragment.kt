package com.redondo.puydufouexperience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.redondo.puydufouexperience.R
import com.redondo.puydufouexperience.databinding.FragmentFavoritosBinding

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EspectaculosAdapter

    private val viewModel: FavoritosViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavoritosBinding.bind(view)

        setupRecyclerView()
        observarViewModel()
    }

    private fun setupRecyclerView() {
        adapter = EspectaculosAdapter(emptyList()) { espectaculo ->
            val action =
                FavoritosFragmentDirections
                    .actionFavoritosFragmentToEspectaculoDetalleFragment(
                        espectaculo.id
                    )

            findNavController().navigate(action)
        }

        binding.recyclerFavoritos.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerFavoritos.adapter = adapter
    }

    private fun observarViewModel() {
        viewModel.listaFavoritos.observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}