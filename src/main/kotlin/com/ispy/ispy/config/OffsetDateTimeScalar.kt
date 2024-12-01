package com.ispy.ispy.config

import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

val OffsetDateTimeScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("OffsetDateTime")
    .description("A scalar for handling Java OffsetDateTime")
    .coercing(object : Coercing<OffsetDateTime, String> {
        override fun serialize(dataFetcherResult: Any): String {
            return (dataFetcherResult as OffsetDateTime).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        override fun parseValue(input: Any): OffsetDateTime {
            return OffsetDateTime.parse(input.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        override fun parseLiteral(input: Any): OffsetDateTime {
            val value = (input as StringValue).value
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }
    })
    .build()