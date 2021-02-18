package com.virtualpairprogrammers.utilities

fun toSentenceCase(input: String) : String = input[0].toUpperCase() + input.substring(1)

fun main() {
    println(toSentenceCase("hello"))
}