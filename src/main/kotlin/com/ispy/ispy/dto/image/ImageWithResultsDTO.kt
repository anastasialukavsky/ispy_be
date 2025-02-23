package com.ispy.ispy.dto.image

import com.ispy.ispy.dto.image.algos.*
import com.ispy.ispy.jooq.tables.*


import kotlin.Metadata

data class ImageWithResultsDTO(
    val image: ImageDTO,
    val elaResults: List<ElaDTO>? = emptyList(),
    val noiseAnalysisResults: List<NoiseAnalysisDTO>? = emptyList(),
    val metadata: List<MetadataDTO>? = emptyList(),
    val historicalWeather: List<HistoricalWeatherDTO>? = emptyList(),
    val deepLearningWeather: List<DeepLearningWeatherDTO>? = emptyList(),
    val geolocation: List<GeolocationDTO>? = emptyList(),
)
