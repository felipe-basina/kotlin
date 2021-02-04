package com.virtualpairprogrammers.learningkotlin.kotlin

import kotlin.math.roundToInt

fun main() {
    val myDouble = 21.4
    println("Is myDouble a Double? ${myDouble is Double}")
    println("myDouble is a ${myDouble::class.qualifiedName}")
    println("myDouble's javaClass is ${myDouble.javaClass}")

    val myInteger = myDouble.roundToInt()
    println("myInteger is a ${myInteger::class.qualifiedName}")

//    var name: String = "Test"
//    val surname: String = "Another" // Immutable variable
//    name = "Changed..."
//    System.out.println(name + " " + surname)

    var name = "Test"
    val surname= "Another"
    println("Hello $name ${surname.toUpperCase()}") // String templates
    println("Your first name has ${name.length} characters")

    val story = """It was a dark and stormy night
        |A foul smell crept across the city
        |Jane wondered what time it was, and when it would be daylight.""".trimMargin("|")
    println(story)

    val changedStory = story.replaceAfterLast("it", " would be dawn.")
    println(changedStory)
    println(changedStory.reversed())
}