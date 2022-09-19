package spring.lab.data

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import spring.lab.data.service.LockTableService
import java.util.UUID
import kotlin.system.exitProcess

@EnableAsync
@SpringBootApplication
class LockTableMainApplication

private val log: Logger = LoggerFactory.getLogger(LockTableMainApplication::class.java)

fun main(args: Array<String>) {
    val applicationContext = runApplication<LockTableMainApplication>(*args)
    val service = applicationContext.getBean(LockTableService::class.java)

    //saveTest(service)
    lockTest(service)


    exitProcess(1)
}

private fun lockTest(service: LockTableService) {
    val dbId = 1L

    service.lockAndUpdate(dbId, "new 1", 18L)
    service.delay(1L)
    service.findAndLock(dbId)
    service.delay(3L)
    service.update(dbId, "239430ba-3059-49a5-ab49-93791689370a", "new 2", 0)

    service.delay(30L)
}

private fun saveTest(service: LockTableService) {
    save(service, false)
    save(service, true)
    save(service, true)
    save(service, false)
    save(service, false)
}

private fun save(service: LockTableService, rollback: Boolean) {
    try {
        service.saving(UUID.randomUUID().toString(), rollback = rollback)
    } catch (ex: Exception) {
        log.error("Error ex=${ex.message}", ex)
    }

    log.info("Total registers in db: ${service.getAllSimple().size}")
}