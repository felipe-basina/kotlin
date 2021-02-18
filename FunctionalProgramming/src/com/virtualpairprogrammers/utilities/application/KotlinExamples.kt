package com.virtualpairprogrammers.utilities.application

import com.virtualpairprogrammers.utilities.toSentenceCase

// myFunction is a lambda function which receives a String as parameter and returns another String
fun applySomeFunctionToAString(inputString: String, myFunction: (String) -> String) : String {
 return myFunction(inputString)
}

fun main() {
    val result: String = applySomeFunctionToAString("hello") { x -> x[0].toUpperCase() + x.substring(1) }
    val result1: String = applySomeFunctionToAString("hello") { it.toUpperCase() }
    val result2: String = applySomeFunctionToAString("hello", ::toSentenceCase)
    println(result)
    println(result1)
    println(result2)

    val colors = listOf("red", "green", "blue", "black")

    val upperCaseColors = colors.map { it.toUpperCase() }
    upperCaseColors.forEach { println(it) }

    val colorsStartingWithB = colors.filter { it.startsWith("b") }
    colorsStartingWithB.forEach { println(it) }

    colors.flatMap { if (it.startsWith("b")) listOf(it, it) else listOf(it) }.forEach { println(it) }

    println(colors.reduce { result, value -> "$result, $value" })

    val numbers = colors.map { it.length }
    println(numbers)

    println(numbers.reduce { acc, i -> acc + i })
    println(numbers.sum())
    println(numbers.average())
    println(numbers.count())
    println(numbers.fold(0) { result, value -> result + value }) // With fold we can set an initial value
    println(numbers.fold(0) { result, value -> if (value > result) value else result })

    val myMap = mapOf(1 to "one", 2 to "two", 3 to "three")
    myMap.filterValues { it.startsWith("t") }.forEach { (k, v) -> println("$k $v")  }
    // When using the syntax (k, v) it means destructuring
    myMap.filter { it.value.startsWith("t") }.forEach { (k, v) -> println("$k $v")  }
}