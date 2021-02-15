import java.lang.ArithmeticException

// Exceptions in Kotlin are unchecked
fun main() {
    try {
        println(7 / 0)
    } catch (ae: ArithmeticException) {
        println("caught")
    }

    Thread.sleep(100)
}