package com.virtualpairprogrammers.learningkotlin.kotlin

/**
 * When specifying a new class if it does not have any body the curly braces can be omitted
 */
class Customer2(val name: String,
               val address: String,
               var age: Int)

/**
 * Specifying the class with its member parameters (constructor)
 */
class Customer(val name: String,
               val address: String,
               var age: Int) {

//    val name: String = "The Name"
//    val address: String = "Any address w/o number"
//    var age: Int = 18

//    val name: String
//    val address: String
//    var age: Int

    /**
     * Using explicit constructor
     */
//    constructor(name: String, address: String, age: Int) {
//        this.name = name
//        this.address = address
//        this.age = age
//    }

    /**
     * Creating a second constructor: needs to call the main one
     */
    constructor(name: String, age: Int): this(name, "", age) {

    }
}

class AlternativeClass(name: String, age: Int) {
    var address: String

    init {
        this.address = ""
    }

    constructor(name: String, address: String, age: Int): this(name, age) {
        this.address = address
    }
}

class AnotherAlternativeCustomer(val name: String, var age: Int, val address: String = "") {
    var approved: Boolean = false
    set(value) {
        if (age >= 21) {
            field = value
        } else {
            println("You can't approve a customer under 21 years old")
        }
    }
//    get() {
//        return field
//    }

    /**
     * Methods set/get should be declared after the member attribute
     */
    val nextAge: Int
    get() {
        return age + 1
    }
}

/**
 * It is not needed to call new to create instace of a class
 * It is not usual to call get/set when accessing classes attributes
 */
fun main() {
    val customer = AnotherAlternativeCustomer("The Name", 18, "Any address w/o number")
    //customer.age = 22
    customer.approved = true
    println("${customer.name} is ${customer.age} years old")
    println("${customer.name} is ${customer.approved}")

    val secondCustomer = AnotherAlternativeCustomer("The second one", 19)
    println("${secondCustomer.name} is ${secondCustomer.age} years old, address ${secondCustomer.address}")
    println("${secondCustomer.name} is ${secondCustomer.approved}")
    println("${secondCustomer.name} next age ${secondCustomer.nextAge}")
}