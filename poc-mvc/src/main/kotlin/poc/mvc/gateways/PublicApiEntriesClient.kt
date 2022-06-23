package poc.mvc.gateways

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "PublicApiEntriesClient", url = "\${public.api.entries.url}")
interface PublicApiEntriesClient {

    @GetMapping(path = ["/entries"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getEntries(): EntriesResponse

}

data class EntriesResponse(

    val count: Int,

    val entries: List<Entries>

)

data class Entries(

    val API: String?,

    val Description: String?,

    val Auth: String?,

    val HTTPS: Boolean = false,

    val Cors: String?,

    val Link: String?,

    val Category: String?

)