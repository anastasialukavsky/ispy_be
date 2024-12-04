package com.ispy.ispy.resolver.image.algos

import com.ispy.ispy.dto.image.algos.HistoricalWeatherDTO
import com.ispy.ispy.dto.image.algos.HistoricalWeatherInput
import com.ispy.ispy.service.image.algos.HistoricalWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class HistoricalWeatherController {

    @Autowired
    private lateinit var historicalWeatherService: HistoricalWeatherService

    @MutationMapping(name="saveHistoricalWeather")
    fun saveHistoricalWeather(@Argument(name="input") input: HistoricalWeatherInput): HistoricalWeatherDTO {
        return historicalWeatherService.saveHistoricalWeather(input)
    }
}