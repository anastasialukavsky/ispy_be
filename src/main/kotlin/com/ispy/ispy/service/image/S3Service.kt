package com.ispy.ispy.service.image

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class S3Service {

    private val dotenv = Dotenv.configure().ignoreIfMissing().load()
    private val logger: Logger = LoggerFactory.getLogger(S3Service::class.java)
    private val region = Region.of(dotenv["AWS_REGION"] ?: throw IllegalArgumentException("Missing AWS_REGION in environment"))
    private val bucketName = dotenv["S3_BUCKET_NAME"] ?: throw IllegalArgumentException("Missing S3_BUCKET_NAME in environment")
    private val credentials = StaticCredentialsProvider.create(
        AwsBasicCredentials.create(
            dotenv["AWS_ACCESS_KEY_ID"] ?: throw IllegalArgumentException("Missing AWS_ACCESS_KEY_ID in environment"),
            dotenv["AWS_SECRET_ACCESS_KEY"] ?: throw IllegalArgumentException("Missing AWS_SECRET_ACCESS_KEY in environment")
        )
    )


    private val s3Client = S3Client.builder()
        .region(region)
        .credentialsProvider(credentials)
        .build()

    fun generatePresignedUrl(objectKey: String): String {
        val presigner = S3Presigner.builder()
            .region(region)
            .credentialsProvider(credentials)
            .build()

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build()

        val presignRequest = PutObjectPresignRequest.builder()
            .putObjectRequest(putObjectRequest)
            .signatureDuration(Duration.ofMinutes(10))
            .build()

        val presignedRequest = presigner.presignPutObject(presignRequest)
        val url = presignedRequest.url().toString()
        logger.info("Generated presigned PUT URL for objectKey $objectKey: $url")
        return url
    }

    fun generatePresignedGetUrl(fileName: String): String {
        var cleanedFileName = fileName.trim()

        while (cleanedFileName.startsWith("uploads/")) {
            cleanedFileName = cleanedFileName.substringAfter("uploads/")
        }

        val objectKey = "uploads/$cleanedFileName"
        println("Object Key after cleanup: $objectKey")

        val presigner = S3Presigner.builder()
            .region(region)
            .credentialsProvider(credentials)
            .build()

        val getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build()

        val getPresignRequest = GetObjectPresignRequest.builder()
            .getObjectRequest(getObjectRequest)
            .signatureDuration(Duration.ofMinutes(30))
            .build()

        val presignedGetObject = presigner.presignGetObject(getPresignRequest)
        val presignedUrl = presignedGetObject.url().toString()
        println("Generated Presigned GET URL: $presignedUrl")
        return presignedUrl
    }

}
