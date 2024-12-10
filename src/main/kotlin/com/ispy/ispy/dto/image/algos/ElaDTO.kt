package com.ispy.ispy.dto.image.algos

import org.springframework.graphql.data.method.annotation.SchemaMapping

@SchemaMapping(typeName = "ElaResults")
data class ElaDTO(
    val id: Int? = null,
    val imageId: Int?,
    val tamperingLikelihood: Double?,
    val detectedEla: Boolean?
)

data class ElaInputDTO(
    val imageId: Int,
    val tamperingLikelihood: Double,
    val detectedEla: Boolean
)