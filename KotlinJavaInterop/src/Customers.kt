import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

data class Customer(val id: Long, val name: String)

class CustomerDatabase() {
    val customers = listOf(Customer(1, "Matt"),
        Customer(2, "James"),
        Customer(3, "Dianne"),
        Customer(4, "Sally"))

    @Throws(IllegalAccessException::class)
    fun addCustomer(c: Customer) {
        throw IllegalAccessException("You cannot add a customer")
    }

    // Creating a static function
    companion object {
        @JvmStatic // This annotation allows Java code to call this static method through CustomerDatabase.helloWorld
        fun helloWorld() = println("Hello world")
    }
}