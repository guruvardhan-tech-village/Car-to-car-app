﻿package com.c2c.feature.map

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

data class MapState(val vehicleId: String, val lat: Double?, val lon: Double?)

@Composable
fun MapScreen(state: MapState) {
  val mapView = rememberMapViewWithLifecycle()

  AndroidView(
    factory = { mapView },
    modifier = Modifier.fillMaxSize()
  ) { map ->
    val lat = state.lat
    val lon = state.lon
    if (lat != null && lon != null) {
      map.getMapAsync { googleMap ->
        val pos = LatLng(lat, lon)
        googleMap.isTrafficEnabled = true
        googleMap.clear()
        googleMap.addMarker(
          MarkerOptions().position(pos).title("Vehicle: ${state.vehicleId}")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15f))
      }
    }
  }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
  val context = LocalContext.current
  val mapView = remember { MapView(context) }

  val lifecycleOwner = LocalLifecycleOwner.current
  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      when (event) {
        Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
        Lifecycle.Event.ON_START -> mapView.onStart()
        Lifecycle.Event.ON_RESUME -> mapView.onResume()
        Lifecycle.Event.ON_PAUSE -> mapView.onPause()
        Lifecycle.Event.ON_STOP -> mapView.onStop()
        Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
        else -> {}
      }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

  return mapView
}
