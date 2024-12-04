package com.ispy.ispy.dto.image

import com.fasterxml.jackson.databind.JsonNode
import org.jooq.JSON

data class MetadataDTO(
    val metadataId: Int? = null,
    val imageId: Int,
    val metadata: JsonNode,
)

data class MetadataInput(
    val imageId: Int,
    val metadata:  Map<String, Any>
)