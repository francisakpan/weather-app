package com.francis.weatherap.core.location.impl

import android.annotation.SuppressLint
import android.content.Context
import com.francis.weatherap.core.location.def.LocationProvider
import com.francis.weatherap.core.types.location.Coordinates
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class LocationProviderImpl @Inject constructor(
    @ApplicationContext context: Context,
) : LocationProvider {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Coordinates? = suspendCoroutine { cont ->
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            null,
        )
            .addOnSuccessListener { location ->
                cont.resume(
                    location?.let { loc ->
                        Coordinates(
                            longitude = loc.longitude,
                            latitude = loc.latitude,
                        )
                    }
                )
            }
            .addOnFailureListener {
                cont.resume(null)
            }
    }
}
