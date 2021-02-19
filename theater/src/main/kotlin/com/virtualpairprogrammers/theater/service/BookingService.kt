package com.virtualpairprogrammers.theater.service

import com.virtualpairprogrammers.theater.data.BookingRepository
import com.virtualpairprogrammers.theater.domain.Booking
import com.virtualpairprogrammers.theater.domain.Performance
import com.virtualpairprogrammers.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookingService {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    fun isSeatFree(seat: Seat, performance: Performance?) : Boolean {
        if (performance == null) {
            return false
        }
        val booked = this.findBookedSeat(seat, performance)
        return booked == null || booked.performance.id != performance.id
    }

    fun findBookedSeat(seat: Seat, performance: Performance?) : Booking? {
        val bookings: List<Booking> = this.bookingRepository.findAll()
        return bookings.filter { it.seat == seat && it.performance.id == performance!!.id }.getOrNull(0)
    }

    fun reserveSeat(seat: Seat, performance: Performance, customerName: String) : Booking {
        val booking = Booking(0, customerName)
        booking.seat = seat
        booking.performance = performance
        return this.bookingRepository.save(booking)
    }

}