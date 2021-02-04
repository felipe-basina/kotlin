package com.virtualpairprogrammers.learningkotlin.kotlin

fun main() {
    var name: String = "Test"
    val surname: String = "Another" // Immutable variable

    name = "Changed..."
    System.out.println(name + " " + surname)
}