package com.api.examples.api

import com.api.examples.model.FakeModel
import com.api.examples.repository.FakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping(path = [ "/fake" ], produces = [ MediaType.APPLICATION_JSON_VALUE ])
class FakeAPI {

    @Autowired
    lateinit var fakeRepository: FakeRepository

    @RequestMapping(path = [ "/all" ])
    fun all(): MutableIterable<FakeModel> {
        return this.fakeRepository.findAll()
    }

    @RequestMapping(path = [ "/{id}" ])
    fun byId(@PathVariable(value = "id") id: Long): FakeModel? {
        return this.fakeRepository.findByIdOrNull(id)
    }

}