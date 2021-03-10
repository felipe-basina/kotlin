package com.api.examples.api

import com.api.examples.model.FakeModel
import com.api.examples.repository.FakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [ "/fake" ], produces = [ MediaType.APPLICATION_JSON_VALUE ])
class FakeAPI {

    @Autowired
    lateinit var fakeRepository: FakeRepository

    @RequestMapping(path = [ "/all" ])
    fun all(): MutableIterable<FakeModel> {
        return this.fakeRepository.findAll()
    }

}