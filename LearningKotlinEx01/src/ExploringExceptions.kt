import java.io.FileInputStream
import java.lang.ArithmeticException
import kotlin.jvm.Throws

// This annotation is just a hint to show the method may throw the specific exception
// But when this method being called by Java code the compiler will complain forcing the Java code to
// catch or throw it again
@Throws(InterruptedException::class)
fun divide(a: Int, b: Int): Double {
    Thread.sleep(1000)
    return a as Double / b
}

fun printFile() {
    val input = FileInputStream("file.txt")

    // The "use" is equivalent to try with resources in Java
    // It means if any exception was threw then the resource will be closed!
    input.use {
        // An exception could be thrown here
    }
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