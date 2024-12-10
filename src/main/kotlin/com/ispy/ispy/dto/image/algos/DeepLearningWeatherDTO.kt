package com.ispy.ispy.dto.image.algos

data class DeepLearningWeatherDTO(
    val id: Int? = null,
    val imageId: Int?,
    val deepLearningWeather: String?
)

data class DeepLearningWeatherInput(
    val imageId: Int,
    val deepLearningWeather: String
)
