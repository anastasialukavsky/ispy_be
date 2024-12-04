package com.ispy.ispy.service.image

import com.fasterxml.jackson.databind.JsonNode
import com.ispy.ispy.dto.image.MetadataDTO
import com.ispy.spy.jooq.tables.Metadata.Companion.METADATA
import org.jooq.DSLContext
import org.jooq.JSONB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.jooq.impl.DSL

@Service
class MetadataService {

    @Autowired
    lateinit var dsl: DSLContext


    fun saveMetadata(imageId: Int, metadata: JsonNode): MetadataDTO {
        // Convert JsonNode to JSONB
        val metadataJsonb = DSL.value(JSONB.valueOf(metadata.toString()))

        val metadataRecord = dsl.insertInto(METADATA)
            .set(METADATA.IMAGE_ID, imageId)
            .set(METADATA.METADATA_, metadataJsonb)
            .returning()
            .fetchOne() ?: throw Exception("Failed to save metadata")

        return MetadataDTO(
            metadataId = metadataRecord.metadataId,
            imageId = metadataRecord.imageId!!,
            metadata = metadata
        )
    }
}