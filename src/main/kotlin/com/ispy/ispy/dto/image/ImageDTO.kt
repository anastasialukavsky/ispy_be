package com.ispy.ispy.dto.image

import org.yaml.snakeyaml.events.Event
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

data class ImageDTO(
    val imageId: Int? = null,
    val userId: UUID,
    val filePath: String,
    val uploadedAt: OffsetDateTime? = null
    )
