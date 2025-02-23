package com.ispy.ispy.service.image.algos

import com.ispy.ispy.dto.image.algos.GeolocationDTO
import com.ispy.ispy.dto.image.algos.GeolocationInput
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.Geolocation.Companion.GEOLOCATION
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class GeolocationService {

    @Autowired
    lateinit var dslContext: DSLContext

    fun saveGeolocation(input: GeolocationInput): GeolocationDTO {
        val geolocationRecord = dslContext.insertInto(GEOLOCATION)
            .set(GEOLOCATION.IMAGE_ID, input.imageId)
            .set(GEOLOCATION.LATITUDE, input.latitude)
            .set(GEOLOCATION.LONGITUDE, input.longitude)
            .returning()
            .fetchOne() ?: throw Exception("Failed to save Geolocation")

        return GeolocationDTO(
            id = geolocationRecord.id,
            imageId = geolocationRecord.imageId!!,
            latitude = geolocationRecord.latitude!!,
            longitude = geolocationRecord.longitude!!
        )
    }
}