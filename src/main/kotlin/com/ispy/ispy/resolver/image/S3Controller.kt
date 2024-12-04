package com.ispy.ispy.resolver.image

import com.ispy.ispy.service.auth.AuthService
import com.ispy.ispy.service.image.S3Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class S3Controller(val s3Service: S3Service) {

    @GetMapping("/generate-presigned-url")
    fun generatePresignedUrl(@RequestParam fileName: String): String {
        val objectKey = "uploads/$fileName"
        return s3Service.generatePresignedUrl(objectKey)
    }
}