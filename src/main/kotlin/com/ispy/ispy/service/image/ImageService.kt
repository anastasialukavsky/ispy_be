package com.ispy.ispy.service.image

import com.fasterxml.jackson.databind.ObjectMapper
import com.ispy.ispy.dto.image.ImageDTO
import com.ispy.ispy.dto.image.ImageWithResultsDTO
import com.ispy.ispy.dto.image.MetadataDTO
import com.ispy.ispy.dto.image.algos.*
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.records.*
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.references.*
import com.ispy.ispy.utils.authUtils.AuthUtils
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

    fun getUserImagesWithResults(userId: UUID): List<ImageWithResultsDTO> {
        val images = dsl.selectFrom(IMAGES)
            .where(IMAGES.USER_ID.eq(userId))
            .fetch()

        return images.map { imageRecord ->
            val imageId = imageRecord.imageId

            val elaResults = dsl.select(
                ELA_RESULTS.ID,
                ELA_RESULTS.IMAGE_ID,
                ELA_RESULTS.TAMPERING_LIKELIHOOD,
                ELA_RESULTS.DETECTED_ELA
            )
                .from(ELA_RESULTS)
                .where(ELA_RESULTS.IMAGE_ID.eq(imageId))
                .fetchInto(ElaResultsRecord::class.java)
                .map { record ->
                    ElaDTO(
                        id = record.id,
                        imageId = record.imageId,
                        tamperingLikelihood = record.tamperingLikelihood,
                        detectedEla = record.detectedEla
                    )
                } ?: emptyList()

            val noiseAnalysisResults = dsl.select(
                NOISE_ANALYSIS_RESULTS.ID,
                NOISE_ANALYSIS_RESULTS.IMAGE_ID,
                NOISE_ANALYSIS_RESULTS.TAMPERING_LIKELIHOOD,
                NOISE_ANALYSIS_RESULTS.DETECTED_NOISE
            )
                .from(NOISE_ANALYSIS_RESULTS)
                .where(NOISE_ANALYSIS_RESULTS.IMAGE_ID.eq(imageId))
                .fetchInto(NoiseAnalysisResultsRecord::class.java)
                .map { record ->
                    NoiseAnalysisDTO(
                        id = record.id,
                        imageId = record.imageId,
                        tamperingLikelihood = record.tamperingLikelihood,
                        detectedNoise = record.detectedNoise
                    )
                } ?: emptyList()

            val objectMapper = ObjectMapper()

            val metadata = dsl.select(
                METADATA.METADATA_ID,
                METADATA.IMAGE_ID,
                METADATA.METADATA_
            )
                .from(METADATA)
                .where(METADATA.IMAGE_ID.eq(imageId))
                .fetchInto(MetadataRecord::class.java)
                .map { record ->
                    MetadataDTO(
                        metadataId = record.metadataId,
                        imageId = record.imageId,
                        metadata = record.metadata?.let { objectMapper.readTree(it.data()) }
                    )
                } ?: emptyList()

            val historicalWeather = dsl.select(
                HISTORICAL_WEATHER.ID,
                HISTORICAL_WEATHER.IMAGE_ID,
                HISTORICAL_WEATHER.HISTORICAL_WEATHER_
            )
                .from(HISTORICAL_WEATHER)
                .where(HISTORICAL_WEATHER.IMAGE_ID.eq(imageId))
                .fetchInto(HistoricalWeatherRecord::class.java)
                .map { record ->
                    HistoricalWeatherDTO(
                        id = record.id,
                        imageId = record.imageId,
                        historicalWeather = record.historicalWeather
                    )
                } ?: emptyList()

            val deepLearningWeather = dsl.select(
                DEEP_LEARNING_WEATHER_ANALIZER.ID,
                DEEP_LEARNING_WEATHER_ANALIZER.IMAGE_ID,
                DEEP_LEARNING_WEATHER_ANALIZER.DEEP_LEARNING_WEATHER
            )
                .from(DEEP_LEARNING_WEATHER_ANALIZER)
                .where(DEEP_LEARNING_WEATHER_ANALIZER.IMAGE_ID.eq(imageId))
                .fetchInto(DeepLearningWeatherAnalizerRecord::class.java)
                .map { record ->
                    DeepLearningWeatherDTO(
                        id = record.id,
                        imageId = record.imageId,
                        deepLearningWeather = record.deepLearningWeather
                    )
                } ?: emptyList()

            val geolocation = dsl.select(
                GEOLOCATION.ID,
                GEOLOCATION.IMAGE_ID,
                GEOLOCATION.LATITUDE,
                GEOLOCATION.LONGITUDE
            )
                .from(GEOLOCATION)
                .where(GEOLOCATION.IMAGE_ID.eq(imageId))
                .fetchInto(GeolocationRecord::class.java)
                .mapNotNull { record ->
                    record.imageId?.let { imageId ->
                        record.latitude?.let { latitude ->
                            record.longitude?.let { longitude ->
                                GeolocationDTO(
                                    id = record.id,
                                    imageId = imageId,
                                    latitude = latitude,
                                    longitude = longitude
                                )
                            }
                        }
                    }
                }

            ImageWithResultsDTO(
                image = ImageDTO(
                    imageId = imageRecord.imageId,
                    userId = imageRecord.userId ?: throw IllegalStateException("User ID missing"),
                    filePath = imageRecord.filePath ?: throw IllegalStateException("File path missing"),
                    uploadedAt = authUtils.convertToOffsetDateTime(imageRecord.uploadedAt)
                ),
                elaResults = elaResults,
                noiseAnalysisResults = noiseAnalysisResults,
                metadata = metadata,
                historicalWeather = historicalWeather,
                deepLearningWeather = deepLearningWeather,
                geolocation = geolocation
            )
        }
    }


}
