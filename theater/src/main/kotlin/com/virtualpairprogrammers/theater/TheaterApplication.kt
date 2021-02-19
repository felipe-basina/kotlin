package com.virtualpairprogrammers.theater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TheaterApplication

fun main(args: Array<String>) {
	runApplication<TheaterApplication>(*args)
}