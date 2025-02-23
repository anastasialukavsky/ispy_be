package com.ispy.ispy.service.image.algos

import com.ispy.ispy.dto.image.algos.DeepLearningWeatherDTO
import com.ispy.ispy.dto.image.algos.DeepLearningWeatherInput
import com.ispy.ispy.jooq.tables.references.DEEP_LEARNING_WEATHER_ANALIZER
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DeepLearningWeatherService {

    @Autowired
    lateinit var dslContext: DSLContext

    fun saveDeepLearningWeatherAnalizerResults(input: DeepLearningWeatherInput): DeepLearningWeatherDTO {
        val deepLearningWeatherRecord = dslContext.insertInto(DEEP_LEARNING_WEATHER_ANALIZER)
            .set(DEEP_LEARNING_WEATHER_ANALIZER.IMAGE_ID, input.imageId)
            .set(DEEP_LEARNING_WEATHER_ANALIZER.DEEP_LEARNING_WEATHER, input.deepLearningWeather)
            .returning()
            .fetchOne() ?: throw Exception("Failed to save DeepLearningWeather")

        return DeepLearningWeatherDTO(
            id = deepLearningWeatherRecord.id,
            imageId = deepLearningWeatherRecord.imageId!!,
            deepLearningWeather = deepLearningWeatherRecord.deepLearningWeather!!
        )
    }
}