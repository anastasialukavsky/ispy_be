package com.ispy.ispy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.ispy.ispy"])
@EnableConfigurationProperties
class IspyApplication

fun main(args: Array<String>) {
	runApplication<IspyApplication>(*args)
}
