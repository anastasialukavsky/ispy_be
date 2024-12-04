package com.ispy.ispy.resolver.image

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.ispy.ispy.dto.image.MetadataDTO
import com.ispy.ispy.dto.image.MetadataInput
import com.ispy.ispy.service.image.MetadataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class MetadataController {

    @Autowired
    private lateinit var metadataService: MetadataService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MutationMapping(name = "saveMetadata")
    fun saveMetadata(@Argument(name = "input") input: MetadataInput): MetadataDTO {
        val metadataJsonNode: JsonNode = objectMapper.valueToTree(input.metadata)
        return metadataService.saveMetadata(input.imageId, metadataJsonNode)
    }
}
