package spring.lab.data

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import spring.lab.data.service.LockTableService
import java.util.*
import kotlin.system.exitProcess

@EnableAsync
@SpringBootApplication
class LockTableMainApplication

private val log: Logger = LoggerFactory.getLogger(LockTableMainApplication::class.java)

fun main(args: Array<String>) {
    val applicationContext = runApplication<LockTableMainApplication>(*args)
    val service = applicationContext.getBean(LockTableService::class.java)

//    saveTest(service)
//    service.delay(20L)
    lockTest(service)


    exitProcess(1)
}

private fun lockTest(service: LockTableService) {
    val dbId = 1L

    service.lockAndUpdate(dbId, "new 1", 18L)
    service.delay(1L)
    service.findAndLock(dbId)
    service.delay(3L)
//    service.findByIdCustom(dbId)
    service.update(dbId, "6caa7162-8f34-4b89-adaf-68be771a5a4a", "new 2", 1)

    service.delay(30L)
}

private fun saveTest(service: LockTableService) {
    save(service, false, 100)
    save(service, true)
    save(service, true)
    save(service, false, 200)
    save(service, false, 300)
}

private fun save(service: LockTableService, rollback: Boolean, counter: Long = -1L) {
    try {
        service.saving(UUID.randomUUID().toString(), counter, rollback = rollback)
    } catch (ex: Exception) {
        log.error("Error ex=${ex.message}", ex)
    }

    log.info("Total registers in db: ${service.getAllSimple().size}")
}