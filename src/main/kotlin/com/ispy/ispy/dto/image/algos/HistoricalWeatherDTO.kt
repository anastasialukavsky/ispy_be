package com.ispy.ispy.dto.image.algos

data class HistoricalWeatherDTO(
    val id: Int? = null,
    val imageId: Int?,
    val historicalWeather: String?
)

data class HistoricalWeatherInput(
    val imageId: Int,
    val historicalWeather: String
)