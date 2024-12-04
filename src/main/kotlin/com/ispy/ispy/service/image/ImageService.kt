package com.ispy.ispy.service.image

import com.ispy.ispy.dto.image.ImageDTO
import com.ispy.ispy.utils.authUtils.AuthUtils
import com.ispy.spy.jooq.tables.references.IMAGES
import com.ispy.spy.jooq.tables.references.USERS
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ImageService {
    @Autowired
    lateinit var dsl: DSLContext

    @Autowired
    lateinit var authUtils: AuthUtils

    fun saveImage(input: ImageDTO): ImageDTO {

        val imageRecord = dsl.insertInto(IMAGES)
            .set(IMAGES.USER_ID, input.userId)
            .set(IMAGES.FILE_PATH, input.filePath)
            .set(IMAGES.UPLOADED_AT, LocalDateTime.now())
            .returning()
            .fetchOne() ?: throw Exception("Failed to save an image")

        return ImageDTO(
            imageId = imageRecord.imageId,
            userId = input.userId,
            filePath = input.filePath,
            uploadedAt = authUtils.convertToOffsetDateTime(imageRecord.uploadedAt)
        )
    }
}