
package com.arteaga.marlon.recipes.utils

import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType

object MapsUtil {

    const val DefaultLatitude = 1.35
    const val DefaultLongitude = 103.87
    const val DefaultZoomCamera = 4f

    fun getMapType(properties: MapProperties): MapType {
        if (MapType.NORMAL == properties.mapType) return MapType.HYBRID
        if (MapType.HYBRID == properties.mapType) return MapType.TERRAIN
        if (MapType.TERRAIN == properties.mapType) return MapType.SATELLITE
        return MapType.NORMAL
    }
}
