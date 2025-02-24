package com.ispy.ispy.resolver.image

import com.ispy.ispy.service.auth.AuthService
import com.ispy.ispy.service.image.S3Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api")
class S3Controller(val s3Service: S3Service) {
    private val logger: Logger = LoggerFactory.getLogger(S3Controller::class.java)

    @GetMapping("/generate-presigned-url")
    fun generatePresignedUrl(@RequestParam fileName: String): String {
        val objectKey = "uploads/$fileName"
        val url = s3Service.generatePresignedUrl(objectKey)
//        logger.info("Generated presigned PUT URL: $url")
        return url
    }

    @GetMapping("/generate-presigned-get-url")
    fun generatePresignedGetUrl(@RequestParam fileName: String): ResponseEntity<String> {
        val objectKey = "uploads/$fileName"
        println("Request for presigned URL received. File name: $fileName")
        try {
            val presignedUrl = s3Service.generatePresignedGetUrl(objectKey)
            println("Generated Presigned URL: $presignedUrl")
            return ResponseEntity.ok(presignedUrl)
        } catch (e: Exception) {
            println("Error generating presigned URL: ${e.message}")
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating presigned URL")
        }
    }
}