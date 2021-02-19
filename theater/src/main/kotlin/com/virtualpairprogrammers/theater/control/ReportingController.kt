package com.virtualpairprogrammers.theater.control

import com.virtualpairprogrammers.theater.service.ReportingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.websocket.server.PathParam
import kotlin.reflect.full.declaredMemberFunctions

@Controller
@RequestMapping(path = [ "/reports" ])
class ReportingController {

    @Autowired
    lateinit var reportingService: ReportingService

    private fun getListOfReports() = this.reportingService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping(path = [""])
    fun reportsAvailable() = ModelAndView("reports", mapOf("reports" to this.getListOfReports()))

    @RequestMapping(path = ["/getReport"])
    fun getReport(@PathParam(value = "report") report: String) : ModelAndView {
        val result = if (report.startsWith("all")) this.reportingService.all_bookings()
                        else this.reportingService.premium_bookings()
        val model = mapOf("result" to result, "reports" to this.getListOfReports())
        return ModelAndView("reports", model)
    }

}