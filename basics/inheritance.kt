// open - modifier allows to overriding
open class Animal(val name: String) {
    open fun speak() {
        println("Uhuuuuuuuu my name is ${name}");
    }
}

// using the "()" to call the default constructor
class Dog(name: String = "Rex") : Animal(name) {
    override fun speak() {
        println("The dog ${name} Whoa whoa");
    }
}

class Cat(name: String = "Enkelin") : Animal(name) {
    override fun speak() {
        println("The cat ${name} meooow");
    }
}

class Bird(name: String = "Voge") : Animal(name)

fun main() {
    val theDog: Animal = Dog();
    theDog.speak();

    val theCat: Animal = Cat("Meowto");
    theCat.speak();

    val theBird: Animal = Bird();
    theBird.speak();
}