package com.virtualpairprogrammers.learningkotlin.kotlin

import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.reflect.full.declaredFunctions

fun isPrime(a: Int) : Boolean {
    val maxNumberToCheck = sqrt(a.toDouble()).roundToInt()
    for (i in 2..maxNumberToCheck) if (a % i == 0) return false
    return true
}

fun main() {

    // To access reflection use `::` myList::class
    val myList = listOf(14, 15, 16, 17, 18, 19, 20)
//    val primeNumbers = myList.filter { isPrime(it) }
    val primeNumbers = myList.filter(::isPrime)
    println(primeNumbers)

    val functions = myList::class.declaredFunctions
    println(functions)
}