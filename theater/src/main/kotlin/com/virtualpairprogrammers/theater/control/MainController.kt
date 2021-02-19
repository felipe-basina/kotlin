package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.data.PerformanceRepository
import com.virtualpairprogrammers.theater.data.SeatRepository
import com.virtualpairprogrammers.theater.domain.Booking
import com.virtualpairprogrammers.theater.domain.Performance
import com.virtualpairprogrammers.theater.domain.Seat
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

    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    @RequestMapping(path = [ "/helloWorld" ])
    fun helloWorld() : ModelAndView = ModelAndView("helloWorld")

    @RequestMapping(path = [ "" ])
    fun homePage() : ModelAndView {
        val model = mapOf("bean" to CheckAvailabilityBackingBean(),
                            "performances" to this.performanceRepository.findAll(),
                            "seatNums" to 1..36,
                            "seatRows" to 'A'..'O')
        return ModelAndView("seatBooking", model)
    }

    @RequestMapping(path = [ "/checkAvailability" ], method = [ RequestMethod.POST ])
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        bean.seat = selectedSeat

        val selectedPerformance = bean.selectedPerformance?.let { this.performanceRepository.findById(it) }
        if (selectedPerformance != null) {
            bean.performance = selectedPerformance.get()
        }

        bean.available = bookingService.isSeatFree(selectedSeat, bean.performance)
        if (bean.available == false) {
            bean.booking = this.bookingService.findBookedSeat(selectedSeat)
        }

        val model = mapOf("bean" to bean,
            "performances" to this.performanceRepository.findAll(),
            "seatNums" to 1..36,
            "seatRows" to 'A'..'O')
        return ModelAndView("seatBooking", model)
    }

    @RequestMapping(path = [ "/booking" ], method = [ RequestMethod.POST ])
    fun booking(bean: CheckAvailabilityBackingBean) : String {
        val booking = Booking(0, bean.customerName)
        booking.seat = bean.seat!!
        booking.performance = bean.performance!!
        this.bookingService.bookingRepository.save(booking)
        return "redirect:/"
    }

    @RequestMapping(path = [ "/bootstrap" ])
    fun createInitialData() : ModelAndView {
        val seats = this.theaterService.seats
        this.seatRepository.saveAll(seats)
        return this.homePage()
    }

}

class CheckAvailabilityBackingBean() {
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var selectedPerformance: Long? = null
    var customerName: String = ""
    var available: Boolean? = null
    var seat: Seat? = null
    var performance: Performance? = null
    var booking: Booking? = null
}