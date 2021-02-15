import java.math.BigDecimal

data class Seat(val row: Int, val num: Int, val price: BigDecimal, val description: String) {
    // NOT EDIT THIS CLASS
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}

class Theater {

    // SEAT PRICES:
    // Seats in rows 14 and 15 cost 14.50
    // Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
    // All other seats in row 1 cost 21.00
    // All other seats cost 18.00

    // SEAT DESCRIPTIONS:
    // Seats in row 15: "Back row"
    // Seats in row 14: "Cheaper seat"
    // All other rows, seats 1 to 3 and 34 to 36: "Restricted View"
    // All other seats in rows 1 and 2 "Best view"
    // All other seats: "Standard seat"

    val seats = this.buildSeats().toList() // THIS MUST BE AN IMMUTABLE LIST

    companion object {
        val especialSeats = listOf(1,2,3,34,35,36)
    }

    private fun seatEspecialDescription(row: Int, num: Int): String {
        return if (especialSeats.contains(num)) {
            "Restricted View"
        } else if (row == 1 || row == 2) {
            "Best view"
        } else {
            "Standard seat"
        }
    }

    private fun seatDescription(row: Int, num: Int): String {
        return when (row) {
            in 1..13 -> this.seatEspecialDescription(row, num)
            14 -> "Cheaper seat"
            else -> "Back row"
        }
    }

    private fun seatEspecialPrice(row: Int, num: Int): BigDecimal {
        val especialPrice = when (num) {
            1,2,3,34,35,36 -> BigDecimal(16.50)
            else -> BigDecimal(18.00)
        }
        if (row == 1 && !especialSeats.contains(num)) {
            return BigDecimal(21.00)
        }
        return especialPrice
    }

    private fun seatPrice(row: Int, num: Int): BigDecimal {
        return when (row) {
            in 1..13 -> this.seatEspecialPrice(row, num)
            else -> BigDecimal(14.50)
        }
    }

    private fun buildSeats(): MutableList<Seat> {
        val seats = mutableListOf<Seat>()
        for (row in 1..15) {
            for (num in 1..36) {
                seats.add(Seat(row, num, this.seatPrice(row, num), this.seatDescription(row, num)))
            }
        }
        return seats
    }

}

fun main() {
    val theater = Theater()
    val cheapSeats = theater.seats.filter { it.price == BigDecimal(14.50) }
    for (seat in cheapSeats) println(seat)
}