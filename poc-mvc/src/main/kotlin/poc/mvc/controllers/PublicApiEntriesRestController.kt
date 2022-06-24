package poc.mvc.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import poc.mvc.gateways.Entries
import poc.mvc.gateways.PublicApiEntriesClient
import poc.mvc.repository.FakeRepo
import java.util.*
import java.util.stream.Collectors

@RestController
class PublicApiEntriesRestController(
    @Autowired private val entriesClient: PublicApiEntriesClient
) {

    @GetMapping(path = ["/public/entries/all/json"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun first10(): List<CustomEntry> {
        val entriesResponse = if (Objects.nonNull(FakeRepo.getEntriesResponse())) {
            FakeRepo.getEntriesResponse()
        } else {
            this.entriesClient.getEntries()
        }
        FakeRepo.addEntriesResponse(entriesResponse!!)
        return entriesResponse
            .entries
            .stream()
            .map { CustomEntry(it) }
            .collect(Collectors.toList())
    }

}

data class CustomEntry(val entry: Entries) {

    val api: String? = entry.API

    val description: String? = entry.Description

    val auth: String? = entry.Auth

    val https: Boolean = entry.HTTPS

    val cors: String? = entry.Cors

    val link: String? = entry.Link

    val category: String? = entry.Category

    val view: String = "<a href=\"/public/entries/all/${api}\">view</a>"

}