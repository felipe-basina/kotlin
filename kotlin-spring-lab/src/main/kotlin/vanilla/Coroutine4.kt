package vanilla

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

val random = Random.nextInt(0, 999999999)

// Referência: https://stackoverflow.com/questions/71836751/run-several-coroutines-in-parallel-with-return-value
fun main() {
    val objects: List<Custom>
    val elapsedTimes = measureTimeMillis {
        runBlocking {
            objects = runNTimes(1000000)
        }
    }

//    val init = System.currentTimeMillis()
//    objects = runNTimesV2(1000000)
//    val elapsedTimes = System.currentTimeMillis() - init

    objects.forEach { println(it) }
    println("Total ${objects.size} | Elaped time $elapsedTimes (ms)")
}

suspend fun runNTimes(times: Int): List<Custom> {
    return coroutineScope {
        (1..times).map {
            async { doSomethingUsefulvv1() }
        }.awaitAll()
    }
}

fun runNTimesV2(times: Int): List<Custom> {
    return (1..times).map {
        doSomethingUsefulvv11()
    }
}

suspend fun doSomethingUsefulvv1(): Custom {
    //println("doSomethingUsefulv1 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    delay(800L) // pretend we are doing something useful here
    return Custom(random.inc(), getRandomString(Random.nextInt(5, 15)))
}

fun doSomethingUsefulvv11(): Custom {
    return Custom(random.inc(), getRandomString(Random.nextInt(5, 15)))
}

suspend fun doSomethingUsefulvv2(): Custom {
   // println("doSomethingUsefulv2 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    delay(750L) // pretend we are doing something useful here, too
    return Custom(29, "vinte e nove")
}

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}