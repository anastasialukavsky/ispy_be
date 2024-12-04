package com.ispy.ispy.resolver.image.algos

import com.ispy.ispy.dto.image.algos.NoiseAnalysisDTO
import com.ispy.ispy.dto.image.algos.NoiseAnalysisInput
import com.ispy.ispy.service.image.algos.NoiseAnalysisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class NoiseAnalysisController {

    @Autowired
    private lateinit var noiseAnalysisService: NoiseAnalysisService

    @MutationMapping(name = "saveNoiseAnalysis")
    fun saveNoiseAnalysis(@Argument(name = "input") input: NoiseAnalysisInput): NoiseAnalysisDTO {
        return noiseAnalysisService.saveNoiseAnalysis(input.imageId, input.tamperingLikelihood, input.detectedNoise)
    }
}
