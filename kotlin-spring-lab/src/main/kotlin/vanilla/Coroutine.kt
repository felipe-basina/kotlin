package vanilla

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("starting: ${Thread.currentThread().name}")
    val cores = Runtime.getRuntime().availableProcessors()
    println("number of cores: $cores")

    //helloWorld()
    //counter()
    //deferredValue()
    //explicitJob()
    //explicitJobWithList()
//    doSomethingUseful()
//    doSomethingUsefulAlternative()
    customObjects2()
    println("-----------------------")
    customObjects()
}

suspend fun counter() = coroutineScope {
    launch {
        println("${Thread.currentThread().name}, 1")

        delay(1000L)
        println("${Thread.currentThread().name}, 2")

        println("${Thread.currentThread().name}, 3")
    }

    println("${Thread.currentThread().name}, 4")
}

fun helloWorld() = runBlocking {
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello")
}

fun printDots() = runBlocking {
    val jobs = (1..100_000).map {
        launch {
            delay(1000L)
            print(".")
        }
    }
    jobs.forEach { it.join() }
}

suspend fun deferredValue() = coroutineScope {
    val job: Deferred<Int> = async {
        println("coroutine: ${Thread.currentThread().name}")
        value()
    }

    runBlocking {
        val value = job.await()
        println("vanilla.value is: $value")
    }

    println("done ${Thread.currentThread().name}")
}

suspend fun value(): Int {
    delay(200)
    println("in value")
    return 2
}

fun explicitJob() = runBlocking {
    val job = launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
    Thread.sleep(5000L)
    job.join()
    println("Done!")
}

fun explicitJobWithList() = runBlocking {
    val job: Deferred<List<Int>> = async {
        delay(3000L)
        println("World!")
        intList()
    }
    println("Hello")
    println("Itens: ${job.await()}")
    println("Done!")
}

fun intList(): List<Int> {
    return (1..10).map { it -> it * 2 }.toList()
}

fun doSomethingUseful() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

fun doSomethingUsefulAlternative() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

fun customObjects() = runBlocking {
    val objects = mutableListOf<Deferred<Custom>>()

    val init = System.currentTimeMillis()
    runBlocking {
        (1..10000000).forEach {
            val response: Deferred<Custom> = async() {
                if (it % 2 == 0) {
                    doSomethingUseful2()
                } else {
                    doSomethingUseful1()
                }
            }
            objects.add(response)
        }
    }

    val contents = mutableListOf<Custom>()


    runBlocking {
        objects.forEach {
            contents.add(it.await())
        }
    }
    val end = System.currentTimeMillis()

    println("Size list ${contents.size}")
    println("Total time ${end - init} ms")
    println(contents.filter { it.id == 13 }.size)
    println(contents.filter { it.id == 29 }.size)
}

fun customObjects2() {
    val contents = mutableListOf<Custom>()

    val init = System.currentTimeMillis()
    (1..10000000).forEach {
        val response = if (it % 2 == 0) {
            doSomethingUseful22()
        } else {
            doSomethingUseful11()
        }
        contents.add(response)
    }
    val end = System.currentTimeMillis()

    println("Size list ${contents.size}")
    println("Total time ${end - init} ms")
    println(contents.filter { it.id == 13 }.size)
    println(contents.filter { it.id == 29 }.size)
}

suspend fun doSomethingUseful1(): Custom {
    //delay(5000L) // pretend we are doing something useful here
    return Custom(13, "treze")
}

suspend fun doSomethingUseful2(): Custom {
    //delay(6000L) // pretend we are doing something useful here, too
    return Custom(29, "vinte e nove")
}

fun doSomethingUseful11(): Custom {
    return Custom(13, "treze")
}

fun doSomethingUseful22(): Custom {
    return Custom(29, "vinte e nove")
}

data class Custom(val id: Int, val description: String)