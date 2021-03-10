package com.api.examples.api

import com.api.examples.model.FakeModel
import com.api.examples.repository.FakeRepository
import com.api.examples.service.FakeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(path = [ "/fake" ], produces = [ MediaType.APPLICATION_JSON_VALUE ])
class FakeAPI {

    @Autowired
    lateinit var fakeRepository: FakeRepository

    @Autowired
    lateinit var fakeService: FakeService

    @RequestMapping(path = [ "/all" ])
    fun all(): MutableIterable<FakeModel> {
        return this.fakeRepository.findAll()
    }

    @RequestMapping(path = [ "/{id}" ])
    fun byId(@PathVariable(value = "id") id: Long): FakeModel? {
        return this.fakeRepository.findByIdOrNull(id)
    }

    @RequestMapping(path = [ "/upload" ], method = [ RequestMethod.POST ])
    fun fileUpload(@RequestParam(value = "file") file: MultipartFile) {
        this.fakeService.save(file)
    }

}