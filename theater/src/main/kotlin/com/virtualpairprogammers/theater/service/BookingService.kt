package com.virtualpairprogammers.theater.service

import com.virtualpairprogammers.theater.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService {

    fun isSeatFree(seat: Seat) : Boolean = true

}