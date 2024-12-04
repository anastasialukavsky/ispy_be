package com.ispy.ispy.service.image.algos

import com.ispy.ispy.dto.image.algos.HistoricalWeatherDTO
import com.ispy.ispy.dto.image.algos.HistoricalWeatherInput
import com.ispy.spy.jooq.tables.references.HISTORICAL_WEATHER
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HistoricalWeatherService {

    @Autowired
    lateinit var dslContext: DSLContext

    fun saveHistoricalWeather(input: HistoricalWeatherInput): HistoricalWeatherDTO {
        val historicalWeatherRecord = dslContext.insertInto(HISTORICAL_WEATHER)
            .set(HISTORICAL_WEATHER.IMAGE_ID, input.imageId)
            .set(HISTORICAL_WEATHER.HISTORICAL_WEATHER_, input.historicalWeather)
            .returning()
            .fetchOne() ?: throw Exception("Failed to save historical weather")

        return HistoricalWeatherDTO(
            id = historicalWeatherRecord.id,
            imageId = historicalWeatherRecord.imageId!!,
            historicalWeather = input.historicalWeather,
        )
    }
}