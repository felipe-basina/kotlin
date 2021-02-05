package com.virtualpairprogrammers.learningkotlin.kotlin

fun main() {
    var name: String? = null // ? Allows to set null to a variable

    //name = "Test"

//    if (name != null) { // OK TO DO THAT
//        println(name.toUpperCase())
//    }

//    println("$name".toUpperCase()) // OK TO DO THAT

    println(name?.toUpperCase()) // The same as the code at line 8
//    println(name!!.toUpperCase()) // !! indicates the variable is GUARANTEED to be NOT NULL. CAUTION!!

    var address = null // The variable is not NULL at this point but an instance to class Nothing

    //address = "Hello" // This will not work because as address is type of Nothing it cannot identify the correct type
    // but if it has been declared as var address: String? = null, it will work normally. See below:
    var aString: String? = null
    aString = "Value"

    var myInteger = 7
    // ... the variable will not accept myInteger = null, but

    var myOtherInteger: Int? = null
    myOtherInteger = null
    // it is ok!
}