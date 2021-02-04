    package com.virtualpairprogrammers.learningkotlin.kotlin

    import java.math.BigDecimal
    import java.util.*

    fun main() {
        // String name = "Test";
        var result: Any // Any is equivalent to Object in Java code

        val randomNumber = Random().nextInt(3)

        if (randomNumber == 1) {
            result = BigDecimal(30)
        } else {
            result = "Hello"
        }

        println("Result is currently: $result")

        if (result is BigDecimal) { // is equivalent to instanceof in Java code
            result = result.add(BigDecimal(47))
        } else {
            val tempResult = result as String // casting ()
            result = tempResult.toUpperCase()
        }

        println("Result is currently: $result")
    }