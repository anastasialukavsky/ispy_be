package com.ispy.ispy.config

import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQLConfig : RuntimeWiringConfigurer {

    override fun configure(builder: RuntimeWiring.Builder) {
        builder
            .scalar(OffsetDateTimeScalar)
            .scalar(ExtendedScalars.UUID)
            .scalar(ExtendedScalars.DateTime)

    }
}
