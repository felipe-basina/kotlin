package com.virtualpairprogrammers.learningkotlin.kotlin

import java.util.*


// Examples os TOP LEVEL FUNCTIONS which means functions without classes

// This function can be seen only in this file
// Default functions will be public
private fun printAsString(value: String) : Unit { // Unit = void method
    println(value)
}

//fun addTwoNumbers(one: Double, two: Double) : Double {
//    return one + two
//}

// The method above could be
// fun addTwoNumbers(one: Double, two: Double) : Double = one + two
fun addTwoNumbers(one: Double, two: Double = 3.9) = one + two // With default value

fun printSomeMaths(one: Double, two: Double) {
    println("one + two is ${one + two}")
    println("one - two is ${one - two}")

    fun functionWithinAFunction(a: String) { // Can only be called from the outer function
        println(a)
    }

    functionWithinAFunction("hello there")
}

//fun methodTakesALambda(input: String, action: java.util.function.Function<String, Int>) {
//    println(action.apply(input))
//}

// Using lambda as second parameter
fun methodTakesALambda(input: String, action: (String) -> Int) { // The same as above: function receives a String and returns an Int
    println(action(input))
}

fun main() {
    printAsString("Hello World")
    println(addTwoNumbers(10.0, 12.25))
    printSomeMaths(25.7, 12.7)
    printSomeMaths(two = 25.7, one = 12.7) // Allows to change the order of the parameters

    // Calls passing the first normal parameter then the body from a lambda code
    methodTakesALambda(Random().nextInt(9).toString()) {
            value -> value.toInt()
    }
}