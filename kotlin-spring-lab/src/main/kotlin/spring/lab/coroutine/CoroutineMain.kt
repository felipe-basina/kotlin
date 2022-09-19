package spring.lab.coroutine

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import spring.lab.forkjoin.model.BeanFJ
import java.util.*
import java.util.stream.IntStream
import java.util.stream.LongStream
import kotlin.system.exitProcess

@SpringBootApplication
class CoroutineMain

private val log = LoggerFactory.getLogger(CoroutineMain::class.java)

var responseFutures = mutableListOf<BeanFJ>()

fun main(args: Array<String>) {
    val appContext = runApplication<CoroutineMain>(*args)
    val incrementService = appContext.getBean(IncrementCoroutineService::class.java)

    val allBeans = createBeans(1000)
    log.info("allBeans size ${allBeans.size}")

    IntStream.rangeClosed(1, 1).forEach {
        val init = System.currentTimeMillis()

        withWait(allBeans, incrementService)

        val elapsedTimeInMs = System.currentTimeMillis() - init

        //responseFutures.forEach { it.status() }

        log.info("Elapsed time ${elapsedTimeInMs / 1000}(s)")
        log.info("Total response objects: ${responseFutures.size}")
        responseFutures = mutableListOf()
    }

    exitProcess(1)
}

private fun withWait(allBeans: List<BeanFJ>, incrementService: IncrementCoroutineService) = runBlocking {
    val tasks = mutableSetOf<Deferred<BeanFJ>>()
    log.info("init")
    allBeans.stream()
        .forEach {
            tasks.add(
                async(Dispatchers.Default) {
                    incrementService.increment(it)
                }
            )
        }
    log.info("end")

    //tasks.awaitAll() // void
    tasks.forEach {
        responseFutures.add(it.await())
    }
}

private fun createBeans(max: Long = 100): List<BeanFJ> {
    val beans = mutableListOf<BeanFJ>()
    LongStream.rangeClosed(1, max).forEach {
        beans.add(
            BeanFJ(
                UUID.randomUUID().toString(),
                it
            )
        )
    }
    return beans
}

