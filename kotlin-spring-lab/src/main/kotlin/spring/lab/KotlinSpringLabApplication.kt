package spring.lab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringLabApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringLabApplication>(*args)
}
