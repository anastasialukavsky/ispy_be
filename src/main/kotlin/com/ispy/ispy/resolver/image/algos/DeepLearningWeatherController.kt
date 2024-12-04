package com.ispy.ispy.resolver.image.algos

import com.ispy.ispy.dto.image.algos.DeepLearningWeatherDTO
import com.ispy.ispy.dto.image.algos.DeepLearningWeatherInput
import com.ispy.ispy.service.image.algos.DeepLearningWeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class DeepLearningWeatherController {

    @Autowired
    private lateinit var deepLearningWeatherService: DeepLearningWeatherService

    @MutationMapping(name="saveDeepLearningWeather")
    fun saveDeepLearningWeather(@Argument(name="input") input: DeepLearningWeatherInput):DeepLearningWeatherDTO {
        return deepLearningWeatherService.saveDeepLearningWeatherAnalizerResults(input)
    }
}