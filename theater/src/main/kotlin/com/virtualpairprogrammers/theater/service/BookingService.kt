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
        val booked = this.findBookedSeat(seat)
        return booked == null || booked.performance != performance
    }

    fun findBookedSeat(seat: Seat) : Booking? {
        val bookings: List<Booking> = this.bookingRepository.findAll()
        return bookings.filter { it.seat == seat }.getOrNull(0)
    }

}