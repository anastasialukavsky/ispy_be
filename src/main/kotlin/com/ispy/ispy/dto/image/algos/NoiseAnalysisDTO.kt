package com.ispy.ispy.dto.image.algos

data class NoiseAnalysisDTO(
    val id: Int? = null,
    val imageId: Int,
    val tamperingLikelihood: Double,
    val detectedNoise: Boolean,
)

data class NoiseAnalysisInput(
    val imageId: Int,
    val tamperingLikelihood: Float,
    val detectedNoise: Boolean,
)