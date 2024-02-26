package spring.lab.schedule

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@EnableScheduling
@SpringBootApplication
class ScheduleMain

private val log = LoggerFactory.getLogger(ScheduleMain::class.java)

fun main(args: Array<String>) {
    runApplication<ScheduleMain>(*args)
}

@Component
class DummySchedule {

    @Scheduled(fixedDelay = 2000)
    fun dummyFixedDelay() {
        log.info("[FixedDelay] Now {}", LocalDateTime.now())
        Thread.sleep(3000L)
    }

    @Scheduled(fixedRate = 3000)
    fun dummyFixedRate() {
        log.info("[FixedRate] Now {}", LocalDateTime.now())
        Thread.sleep(1000L)
    }

}