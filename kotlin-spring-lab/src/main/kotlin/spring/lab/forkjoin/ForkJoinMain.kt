package spring.lab.forkjoin

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import spring.lab.forkjoin.model.BeanFJ
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ForkJoinPool
import java.util.stream.Collectors
import java.util.stream.LongStream
import kotlin.system.exitProcess

@SpringBootApplication
class ForkJoinMain

private val log = LoggerFactory.getLogger(ForkJoinMain::class.java)

fun main(args: Array<String>) {
    val appContext = runApplication<ForkJoinMain>(*args)

    val incrementService = appContext.getBean(IncrementService::class.java)

    val responseFutures = mutableListOf<CompletableFuture<BeanFJ>>()

    val forkJoinPool = ForkJoinPool(8)

    val init = System.currentTimeMillis()

    val allBeans = createBeans()

    try {
        /*forkJoinPool.submit {
            allBeans.parallelStream()
                .forEach {
                    responseFutures.add(
                        incrementService.increment(it)
                    )
                }
        }.get()*/

        allBeans.stream()
            .forEach {
                responseFutures.add(
                    incrementService.incrementV2(it)
                )
            }

        /*forkJoinPool.submit {
            allBeans.stream()
                .forEach {
                    responseFutures.add(
                        incrementService.incrementV2(it)
                    )
                }
        }.get()*/
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        forkJoinPool.shutdown()
    }

    val array = responseFutures.toTypedArray()

    val finalResponses = CompletableFuture.allOf(*array)
        .thenApply {
            responseFutures.stream()
                .map { future -> future.join() }
                .collect(Collectors.toList())
        }.get()

    val elapsedTimeInMs = System.currentTimeMillis() - init

    log.info("Elapsed time ${elapsedTimeInMs / 1000}(s)")
    log.info("Total response objects: ${finalResponses.size}")

    //finalResponses.forEach { it.status() }

    exitProcess(1)
}

fun createBeans(): List<BeanFJ> {
    val beans = mutableListOf<BeanFJ>()
    LongStream.rangeClosed(1, 1000).forEach {
        beans.add(
            BeanFJ(
                UUID.randomUUID().toString(),
                it
            )
        )
    }
    return beans
}

