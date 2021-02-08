package com.virtualpairprogrammers.learningkotlin.kotlin

/**
 * When specifying a new class if it does not have any body the curly braces can be omitted
 */
class Customer2(val name: String,
               val address: String,
               var age: Int)

/**
 * Specifying the class with its member parameters (constructor)
 *
 * The data modify provides methods like toString, hashCode, get/set, copy... and it will
 * possible to use destructuring (component functions)
 */
data class Customer(val name: String,
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

//    fun upperCaseName(): String {
//        return name.toUpperCase()
//    }

    // The same as above
    fun upperCaseName() =
        name.toUpperCase()

    override fun toString() =
        "$name $address $age"

    // Creates a static method
    companion object {
        fun getInstance() = AnotherAlternativeCustomer("Micky", 22, "Some address")
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
    println("${secondCustomer.upperCaseName()} next age ${secondCustomer.nextAge}")
    println("$secondCustomer")
    println(AnotherAlternativeCustomer.getInstance())

    // Creating instances from a data class
    val customer4 = Customer("Sally", 29)
    println(customer4)
    val customer5 = customer4.copy(name="Diane")
    println(customer5)

    // Using destructuring
    val (name, address, age) = customer5
    println("$name $address $age")
}