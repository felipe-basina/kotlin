package vanilla

import kotlinx.coroutines.*
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextDouble
import kotlin.system.measureTimeMillis

// Referência: https://betterprogramming.pub/parallelization-in-kotlin-with-coroutines-91f0c77c5a8
fun main() {
    singleConcurrency()
}

fun noConcurrency() {
    val objects = mutableListOf<Custom>()

    val init = System.currentTimeMillis()
    objects.add(doSomethingUsefulv3())
    objects.add(doSomethingUsefulv4())
    val end = System.currentTimeMillis()
    println("Duration ${end - init}")

    objects.forEach { println(it) }
}

fun singleConcurrency() {
    val objects = mutableListOf<Custom>()

    val init = System.currentTimeMillis()
    runBlocking {
        objects.addAll(listOf(
            async { doSomethingUsefulv1() },
            async { doSomethingUsefulv2() }
        )
            .map { it.await() })
    }
    val end = System.currentTimeMillis()

    println("Duration ${end - init}")

    objects.forEach { println(it) }
}

fun concurrency() {
    val objects = mutableListOf<Custom>()

    val timeSpend = measureTimeMillis {
        (1..10000).forEach { _ ->
            runBlocking {
                objects.addAll(listOf(
                    async { doSomethingUsefulv1() },
                    async { doSomethingUsefulv2() }
                )
                    .map { it.await() })
            }
        }
    }
    println("Time spend: ${timeSpend}ms")
}

suspend fun doSomethingUsefulv1(): Custom {
    println("doSomethingUsefulv1 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    delay(800L) // pretend we are doing something useful here
    return Custom(13, "treze")
}

suspend fun doSomethingUsefulv2(): Custom {
    println("doSomethingUsefulv2 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    delay(750L) // pretend we are doing something useful here, too
    return Custom(29, "vinte e nove")
}

fun doSomethingUsefulv3(): Custom {
    println("doSomethingUsefulv1 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    return Custom(13, "treze")
}

fun doSomethingUsefulv4(): Custom {
    println("doSomethingUsefulv2 | ${System.currentTimeMillis()} | ${Thread.currentThread().name}")
    return Custom(29, "vinte e nove")
}