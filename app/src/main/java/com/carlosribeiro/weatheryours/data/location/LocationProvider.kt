package com.carlosribeiro.weatheryours.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationProvider(
    context: Context
) {

    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(
        onSuccess: (lat: Double, lon: Double) -> Unit,
        onError: () -> Unit
    ) {
        client.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    onSuccess(location.latitude, location.longitude)
                } else {
                    onError()
                }
            }
            .addOnFailureListener {
                onError()
            }
    }
}
