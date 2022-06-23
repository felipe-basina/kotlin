package poc.mvc.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import poc.mvc.gateways.EntriesResponse
import poc.mvc.gateways.PublicApiEntriesClient
import java.util.*

@Controller
class PublicApiEntriesController(
    @Autowired private val entriesClient: PublicApiEntriesClient
) {

    @RequestMapping(path = ["/index"])
    fun index(): ModelAndView {
        val model = mapOf("bean" to null)
        return ModelAndView("index", model)
    }

    @RequestMapping(path = ["/index2"])
    fun index2(): ModelAndView {
        val model = mapOf("bean" to null)
        return ModelAndView("index2", model)
    }

    @RequestMapping(path = ["/public/entries"])
    fun publicEntries(): ModelAndView {
        val model = mapOf("bean" to null)
        return ModelAndView("content", model)
    }

    @RequestMapping(path = ["/public/entries/all"])
    fun first10(): ModelAndView {
        val entriesResponse = if (Objects.nonNull(CONTENT)) {
            CONTENT
        } else {
            this.entriesClient.getEntries()
        }
        CONTENT = entriesResponse

        val first10 = if (Objects.nonNull(entriesResponse)) {
            entriesResponse!!.entries.subList(0, 25)
        } else {
            emptyList()
        }
        val total = if (Objects.nonNull(entriesResponse)) {
            entriesResponse!!.count
        } else {
            0
        }

        val model = mapOf("bean" to PublicApiEntriesBackingBean(EntriesResponse(total, first10)))
        return ModelAndView("content", model)
    }

    companion object {

        var CONTENT: EntriesResponse? = null

    }

}

class PublicApiEntriesBackingBean(

    val response: EntriesResponse

)