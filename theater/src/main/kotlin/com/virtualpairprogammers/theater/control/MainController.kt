package com.virtualpairprogammers.theater.control

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @RequestMapping(path = [ "/helloWorld" ])
    fun helloWorld() : ModelAndView = ModelAndView("helloWorld")

    @RequestMapping(path = [ "" ])
    fun homePage() : ModelAndView {
        return ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())
    }

    @RequestMapping(path = [ "/checkAvailability" ], method = [ RequestMethod.POST ])
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {
        return ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())
    }

}

class CheckAvailabilityBackingBean() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result: String = ""
}