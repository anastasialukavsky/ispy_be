package com.ispy.ispy.resolver.image.algos

import com.ispy.ispy.dto.image.algos.GeolocationDTO
import com.ispy.ispy.dto.image.algos.GeolocationInput
import com.ispy.ispy.service.image.algos.GeolocationService
import org.jooq.impl.DefaultDSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class GeolocationController {

    @Autowired
    private lateinit var dslContext: DefaultDSLContext

    @Autowired
    private lateinit var geolocationService: GeolocationService

    @MutationMapping(name="saveGeolocation",)
    fun saveGeolocation(@Argument(name="input") input: GeolocationInput): GeolocationDTO {
       return geolocationService.saveGeolocation(input)
    }
}