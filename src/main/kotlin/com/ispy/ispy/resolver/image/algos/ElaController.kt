package com.ispy.ispy.resolver.image.algos

import com.ispy.ispy.dto.image.algos.ElaDTO
import com.ispy.ispy.dto.image.algos.ElaInputDTO
import com.ispy.ispy.service.image.algos.ElaService
import com.ispy.spy.jooq.tables.ElaResults
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class ElaController {

    @Autowired
    private lateinit var elaService: ElaService

    @MutationMapping(name="saveEla")
    fun saveElaResults(@Argument(name="input") input:ElaInputDTO): ElaDTO {
        return elaService.saveEla(input)
    }
}