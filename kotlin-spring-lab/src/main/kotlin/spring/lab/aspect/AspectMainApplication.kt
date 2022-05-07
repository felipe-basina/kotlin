package spring.lab.aspect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class KotlinSpringLabApplication

fun main(args: Array<String>) {
	val application = runApplication<KotlinSpringLabApplication>(*args)
	val fakeService = application.getBean(FakeAService::class.java)
	callService(fakeService, 1)
	callService(fakeService, 0)
	callService(fakeService, 5)
	exitProcess(1)
}

fun callService(fakeAService: FakeAService, param: Int) {
	try {
		fakeAService.fakeA(ParamObj(param))
	} catch (ex : RuntimeException) {
		println("Ex: ${ex.message}")
	}
}
