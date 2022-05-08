package spring.lab.aspect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class KotlinSpringLabApplication

fun main(args: Array<String>) {
    val application = runApplication<KotlinSpringLabApplication>(*args)
    val authorizationComponent = application.getBean(AuthorizationComponent::class.java)
    val fakeService = application.getBean(FakeAService::class.java)
    callService(fakeService, authorizationComponent, 1)
    callService(fakeService, authorizationComponent, 0)
    callService(fakeService, authorizationComponent, 5)
    exitProcess(1)
}

fun callService(fakeAService: FakeAService, authorizationComponent: AuthorizationComponent, param: Int) {
    try {
        fakeAService.fakeA(authorizationComponent, ParamObj(param))
    } catch (ex: RuntimeException) {
        println("Ex: ${ex.message}")
    }
}
