package vanilla

import kotlinx.coroutines.*
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextDouble
import kotlin.system.measureTimeMillis

// Referência: https://betterprogramming.pub/parallelization-in-kotlin-with-coroutines-91f0c77c5a8
fun main() {
    (1..5).forEach { _ ->
        println("-------------------------------")
        val timeSpend = measureTimeMillis {
            runBlocking {
                val canAcceptOrder = (
                        listOf(
                            async { isOnBlockedList("max@mustermann.de") },
                            async { isAlreadyRegistered("max@mustermann.de") },
                            async { checkWorkload() < 0.75 }
                        ) +
                                listOf("A", "B", "C")
                                    .map { async { isItemInStock(it) } }
                        ).map { it.await() }.none { !it }
                println("Order is acceptable? -> $canAcceptOrder")
            }
        }
        println("Time spend: ${timeSpend}ms")
    }
}

suspend fun isOnBlockedList(email: String): Boolean {
    delay(800L)
    val isBlocked = nextBoolean()
    println("IsBlocked? -> $isBlocked")
    return isBlocked
}

suspend fun isAlreadyRegistered(email: String): Boolean {
    delay(750L)
    val isAlreadyRegistered = nextBoolean()
    println("isAlreadyRegistered? -> $isAlreadyRegistered")
    return isAlreadyRegistered
}

suspend fun isItemInStock(id: String): Boolean {
    delay(250L)
    val isItemInStock = nextBoolean()
    println("isItemInStock? -> $isItemInStock")
    return isItemInStock
}

suspend fun checkWorkload(): Double {
    delay(1000L)
    val workLoad = nextDouble()
    println("Workload? -> $workLoad")
    return workLoad
}