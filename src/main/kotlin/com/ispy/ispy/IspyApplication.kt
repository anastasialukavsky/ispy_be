package com.ispy.ispy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IspyApplication

fun main(args: Array<String>) {
	runApplication<IspyApplication>(*args)
}
