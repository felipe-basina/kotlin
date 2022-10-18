package spring.lab.coroutine

import java.io.File
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import spring.lab.forkjoin.model.BeanFJ
import java.io.FileWriter
import java.io.IOException
import java.lang.StringBuilder
import java.util.*
import java.util.stream.IntStream
import java.util.stream.LongStream
import kotlin.system.exitProcess

@SpringBootApplication
class CoroutineMain

private val log = LoggerFactory.getLogger(CoroutineMain::class.java)

var responseFutures = mutableListOf<BeanFJ>()
var stringBuilder = StringBuilder()

fun main(args: Array<String>) {
    val appContext = runApplication<CoroutineMain>(*args)
    val incrementService = appContext.getBean(IncrementCoroutineService::class.java)

    val allBeans = createBeans(100000)
    log.info("allBeans size ${allBeans.size}")
    val chunkedList = breakList(allBeans)
    log.info("chunkedList size ${chunkedList.size}")

    IntStream.rangeClosed(1, 1).forEach {
//    chunkedList.forEach {
        val init = System.currentTimeMillis()

        withWait(allBeans, incrementService)

        val elapsedTimeInMs = System.currentTimeMillis() - init

        //responseFutures.forEach { it.status() }

        log.info("Elapsed time ${elapsedTimeInMs / 1000}(s)")
        log.info("Total response objects: ${responseFutures.size}")

        //responseFutures.forEach { it.status() }

//        saveToFile(responseFutures, deleteExistingFile = true)
        saveToFile(deleteExistingFile = true)
        responseFutures = mutableListOf()
    }

    exitProcess(1)
}

private fun saveToFile(content: List<BeanFJ>, deleteExistingFile: Boolean = false) {
    val path = System.getProperty("user.dir") + "/src/result.csv"
    if (deleteExistingFile) {
        File(path).delete()
    }
    content.forEach {
        try {
            val fw = FileWriter(path, true)
            fw.write(it.printContent())
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    log.info("m=saveToFile, content saved into $path")
}

private fun saveToFile(deleteExistingFile: Boolean = false) {
    val path = System.getProperty("user.dir") + "/src/result.csv"
    if (deleteExistingFile) {
        File(path).delete()
    }
    log.info("m=saveToFile, stringBuilder-length=${stringBuilder.length}")
    try {
        val fw = FileWriter(path, true)
        fw.write(stringBuilder.toString())
        fw.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    log.info("m=saveToFile, content saved into $path")
}

private fun breakList(originalList: List<BeanFJ>, total: Int = 10000): List<List<BeanFJ>> {
    return originalList.chunked(total)
}

private fun withWait(allBeans: List<BeanFJ>, incrementService: IncrementCoroutineService) = runBlocking {
    val tasks = mutableSetOf<Deferred<BeanFJ>>()
    log.info("init")
    allBeans.stream()
//        .parallel()
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
        val bean = it.await()
        responseFutures.add(bean)
        stringBuilder.append(bean.printContent())
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

