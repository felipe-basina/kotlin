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
}