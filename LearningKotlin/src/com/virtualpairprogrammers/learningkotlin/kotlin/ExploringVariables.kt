package com.virtualpairprogrammers.learningkotlin.kotlin

fun main() {
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