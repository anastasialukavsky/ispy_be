package com.ispy.ispy.resolver


import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GreetingResolver {

    @QueryMapping
    fun greeting(): String {
        return "Hello, World!"
    }
}