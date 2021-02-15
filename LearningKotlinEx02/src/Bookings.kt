import java.math.BigDecimal

interface BookingManager {

    val version: String

    fun isSeatFree(seat: Seat): Boolean
    fun reserveSeat(seat: Seat, customerID: Long): Boolean

    fun systemStatus() = "All Operations are Functional"

}

// Creating custom exception (by extending Throwable)
class UnauthorizedUserException : Throwable()

/**
 * By default classes in Kotlin are final which means they are not able to be extended
 * To do this it is needed to use the 'open' modifier
 */
open class BasicBookingManager(authorizationKey: String) : BookingManager {

    override val version = "1.0"

    override fun isSeatFree(seat: Seat) = true
    override fun reserveSeat(seat: Seat, customerID: Long) = false

    init {
        if (authorizationKey != "12345") throw UnauthorizedUserException()
    }

}

class AdvancedBookingManager : BasicBookingManager("12345") {

    override val version = "2.0"

    fun howManyBookings() = 10

}

/**
 * This is an example of adding functions into an existing class, even if it is final
 * So in this file or package the class String has a new method called toSentenceCase()
 */
fun String.toSentenceCase(): String {
    return this[0].toUpperCase() + this.substring(1)
}

fun main() {
    println(AdvancedBookingManager().isSeatFree(Seat(1, 1, BigDecimal.ZERO, "")))

    var greeting = "welcome to the system"
    println(greeting.toSentenceCase())
}