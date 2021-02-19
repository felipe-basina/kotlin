package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.data.SeatRepository
import com.virtualpairprogrammers.theater.service.BookingService
import com.virtualpairprogrammers.theater.service.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var theaterService: TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var seatRepository: SeatRepository

    @RequestMapping(path = [ "/helloWorld" ])
    fun helloWorld() : ModelAndView = ModelAndView("helloWorld")

    @RequestMapping(path = [ "" ])
    fun homePage() : ModelAndView {
        return ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())
    }

    @RequestMapping(path = [ "/checkAvailability" ], method = [ RequestMethod.POST ])
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val result = bookingService.isSeatFree(selectedSeat)
        bean.result = "Seat $selectedSeat is " + if (result) "available" else "booked"
        return ModelAndView("seatBooking", "bean", bean)
    }

    @RequestMapping(path = [ "/bootstrap" ])
    fun createInitialData() : ModelAndView {
        val seats = this.theaterService.seats
        this.seatRepository.saveAll(seats)
        return this.homePage()
    }

}

class CheckAvailabilityBackingBean() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result: String = ""
}