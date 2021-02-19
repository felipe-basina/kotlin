package com.virtualpairprogrammers.theater.service

import com.virtualpairprogrammers.theater.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService {

    fun isSeatFree(seat: Seat) : Boolean = true

}