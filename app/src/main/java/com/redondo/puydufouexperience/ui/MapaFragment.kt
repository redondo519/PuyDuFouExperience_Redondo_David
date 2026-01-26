package com.redondo.puydufouexperience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.redondo.puydufouexperience.R



class MapaFragment : Fragment(R.layout.fragment_mapa), OnMapReadyCallback {

    private val viewModel: MapaViewModel by viewModels()
    private lateinit var googleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val puyDuFou = LatLng(39.8567, -4.0245)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puyDuFou, 16f))

        observarEspectaculos()
        configurarClickMarker()
    }

    private fun observarEspectaculos() {
        viewModel.espectaculos.observe(viewLifecycleOwner) { lista ->
            lista.forEach { espectaculo ->
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(espectaculo.latitud, espectaculo.longitud))
                        .title(espectaculo.nombre)
                )
                marker?.tag = espectaculo.id
            }
        }
    }

    private fun configurarClickMarker() {
        googleMap.setOnMarkerClickListener { marker ->
            val id = marker.tag as? Int ?: return@setOnMarkerClickListener false

            val action =
                MapaFragmentDirections
                    .actionMapaFragmentToEspectaculoDetalleFragment(id)

            findNavController().navigate(action)
            true
        }
    }
}
