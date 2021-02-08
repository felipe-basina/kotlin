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

/**
 * It is not needed to call new to create instace of a class
 * It is not usual to call get/set when accessing classes attributes
 */
fun main() {
    val customer = Customer("The Name", "Any address w/o number", 18)
    customer.age = 22
    println("${customer.name} is ${customer.age} years old")

    val secondCustomer = Customer("The second one", 19)
    println("${secondCustomer.name} is ${secondCustomer.age} years old, address ${secondCustomer.address}")
}