package com.ispy.ispy.service.image.algos

import com.ispy.ispy.dto.image.algos.ElaDTO
import com.ispy.ispy.dto.image.algos.ElaInputDTO
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.references.ELA_RESULTS
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElaService {

    @Autowired
    lateinit var dslContext: DSLContext

    fun saveEla(input: ElaInputDTO): ElaDTO {
        val elaRecord = dslContext.insertInto(ELA_RESULTS)
            .set(ELA_RESULTS.IMAGE_ID, input.imageId)
            .set(ELA_RESULTS.DETECTED_ELA, input.detectedEla)
            .set(ELA_RESULTS.TAMPERING_LIKELIHOOD, input.tamperingLikelihood.toDouble())
            .returning()
            .fetchOne() ?: throw Exception("Failed to save ela analysis results")

        return ElaDTO(
            id = elaRecord.id,
            imageId = elaRecord.imageId!!,
            detectedEla = elaRecord.detectedEla!!,
            tamperingLikelihood  = elaRecord.tamperingLikelihood!!

        )
    }
}