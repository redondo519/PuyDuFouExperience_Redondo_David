package com.redondo.puydufouexperience.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.redondo.puydufouexperience.R


class MapaFragment : Fragment(R.layout.fragment_mapa), OnMapReadyCallback {

    private val viewModel: MapaViewModel by viewModels()
    private lateinit var googleMap: GoogleMap

    private val locationPermissionRequest =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                activarLocalizacionUsuario()
            }
        }


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

        googleMap.setOnMarkerClickListener { marker ->
            marker.showInfoWindow()
            true
        }

        googleMap.setOnInfoWindowClickListener { marker ->
            val id = marker.tag as? Int ?: return@setOnInfoWindowClickListener

            val action =
                MapaFragmentDirections
                    .actionMapaFragmentToEspectaculoDetalleFragment(id)

            findNavController().navigate(action)
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            activarLocalizacionUsuario()
        } else {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }


    private fun observarEspectaculos() {
        viewModel.espectaculos.observe(viewLifecycleOwner) { lista ->
            lista.forEach { espectaculo ->
                val icono = obtenerIconoPorTipo(espectaculo.tipo)
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .position(
                            LatLng(
                                espectaculo.latitud,
                                espectaculo.longitud
                            )
                        ) //posicion del espectaculo pasada por parametro
                        .title(espectaculo.nombre)  //titulo del espectaculo
                        .snippet(espectaculo.descripcion)//resumen del espectaculo
                        .icon(icono)
                )
                marker?.tag = espectaculo.id
            }
        }
    }

    /*
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

     */

    private fun activarLocalizacionUsuario() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        googleMap.isMyLocationEnabled = true
    }

    /**
     * Devuelve un BitmapDescriptor a partir de un tipo de espectáculo.
     * Funciona tanto con vectores (.xml) como con PNG/JPG.
     */
    private fun obtenerIconoPorTipo(tipo: String): BitmapDescriptor {
        val drawableId = when (tipo.lowercase()) {
            "espectaculo" -> R.drawable.ic_espectaculo
            "restaurante" -> R.drawable.ic_restaurante
            "tienda" -> R.drawable.ic_tienda
            else -> R.drawable.ic_esp_default
        }

        val drawable = ContextCompat.getDrawable(requireContext(), drawableId)
            ?: return BitmapDescriptorFactory.defaultMarker() // fallback si no existe

        // Convertimos el drawable (vector o png) en Bitmap
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth.coerceAtLeast(1),
            drawable.intrinsicHeight.coerceAtLeast(1),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


}
