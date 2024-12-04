package com.ispy.ispy.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DotenvConfig {
    @Bean
    fun dotenv(): Dotenv {
        return Dotenv.configure().ignoreIfMissing().load()
    }
}