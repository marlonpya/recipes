
package com.arteaga.marlon.data.fake

import com.arteaga.marlon.data.entities.LocationEntity

object LocationEntityTestData {
    const val latitude = "42.12345"
    const val longitude = "12.34567"
    fun createLocationEntity(): LocationEntity {
        return LocationEntity(
            latitude = latitude,
            longitude = longitude
        )
    }
}
