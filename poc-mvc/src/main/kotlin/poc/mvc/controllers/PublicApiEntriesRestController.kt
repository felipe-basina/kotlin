package poc.mvc.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import poc.mvc.gateways.Entries
import poc.mvc.gateways.PublicApiEntriesClient
import java.util.*

@RestController
class PublicApiEntriesRestController(
    @Autowired private val entriesClient: PublicApiEntriesClient
) {

    @GetMapping(path = ["/public/entries/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun first10(): List<Entries> {
        val entriesResponse = this.entriesClient.getEntries()
        val first10 = if (Objects.nonNull(entriesResponse)) {
            entriesResponse.entries.subList(0, 11)
        } else {
            emptyList()
        }
        val total = if (Objects.nonNull(entriesResponse)) {
            entriesResponse.count
        } else {
            0
        }
        return first10
    }

}