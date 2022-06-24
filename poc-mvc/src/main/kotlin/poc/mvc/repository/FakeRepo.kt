package poc.mvc.repository

import poc.mvc.gateways.Entries
import poc.mvc.gateways.EntriesResponse

class FakeRepo {

    companion object {

        fun addEntriesResponse(entriesResponse: EntriesResponse) {
            ALL_ENTRIES_RESPONSE = entriesResponse
        }

        fun getEntriesResponse(): EntriesResponse? {
            return ALL_ENTRIES_RESPONSE
        }

        fun findByApi(api: String): Entries {
            return ALL_ENTRIES_RESPONSE!!
                .entries
                .stream()
                .filter { it.API == api }
                .findFirst()
                .get()
        }

        private var ALL_ENTRIES_RESPONSE: EntriesResponse? = null

    }

}