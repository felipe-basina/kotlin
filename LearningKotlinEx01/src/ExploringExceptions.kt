import java.lang.ArithmeticException
import kotlin.jvm.Throws

// This annotation is just a hint to show the method my throw the specific exception
// But when this method being called by Java code the compiler will complain forcing the Java code to
// catch or throw it again
@Throws(InterruptedException::class)
fun divide(a: Int, b: Int): Double {
    Thread.sleep(1000)
    return a as Double / b
}

// Exceptions in Kotlin are unchecked
fun main() {
    try {
        println(7 / 0)
    } catch (ae: ArithmeticException) {
        println("caught")
    }

    Thread.sleep(100)
}