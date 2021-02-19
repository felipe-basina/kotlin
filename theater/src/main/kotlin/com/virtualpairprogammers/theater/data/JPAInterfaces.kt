package com.virtualpairprogammers.theater.data

import com.virtualpairprogammers.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>