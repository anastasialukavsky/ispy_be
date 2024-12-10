package com.ispy.ispy.dto.image.algos

data class GeolocationDTO(
    val id: Int? = null,
    val imageId: Int?,
    val latitude: Double?,
    val longitude: Double?,
)

data class GeolocationInput(
    val imageId: Int,
    val latitude: Double,
    val longitude: Double,
    )

