package com.ispy.ispy.resolver.image

import com.ispy.ispy.dto.image.ImageDTO
import com.ispy.ispy.dto.image.ImageWithResultsDTO
import com.ispy.ispy.service.image.ImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.*

@Controller
class ImageController {

    @Autowired
    private lateinit var imageService: ImageService

    @MutationMapping(name="saveImage")
    fun saveImage(@Argument(name="input") input: ImageDTO): ImageDTO {
        return imageService.saveImage(input)
    }

    @QueryMapping(name="getUserImagesWithResults")
    fun getUserImagesWithResults(@Argument(name="userId") userId: UUID): List<ImageWithResultsDTO> {
        return imageService.getUserImagesWithResults(userId)
    }
}