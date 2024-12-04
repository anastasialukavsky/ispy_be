package com.ispy.ispy.service.image.algos


import com.ispy.ispy.dto.image.algos.NoiseAnalysisDTO
import com.ispy.spy.jooq.tables.references.NOISE_ANALYSIS_RESULTS
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.jooq.impl.DSL

@Service
class NoiseAnalysisService {

    @Autowired
    lateinit var dsl: DSLContext

    fun saveNoiseAnalysis(imageId: Int, tamperingLikelihood: Float, detectedNoise: Boolean): NoiseAnalysisDTO {
        val noiseAnalysisRecord = dsl.insertInto(NOISE_ANALYSIS_RESULTS)
            .set(NOISE_ANALYSIS_RESULTS.IMAGE_ID, imageId)
            .set(NOISE_ANALYSIS_RESULTS.TAMPERING_LIKELIHOOD, tamperingLikelihood.toDouble())
            .set(NOISE_ANALYSIS_RESULTS.DETECTED_NOISE, detectedNoise)
            .returning()
            .fetchOne() ?: throw Exception("Failed to save noise analysis result")

        return NoiseAnalysisDTO(
            id = noiseAnalysisRecord.id,
            imageId = noiseAnalysisRecord.imageId!!,
            tamperingLikelihood = noiseAnalysisRecord.tamperingLikelihood!!,
            detectedNoise = noiseAnalysisRecord.detectedNoise!!,
        )
    }



}
