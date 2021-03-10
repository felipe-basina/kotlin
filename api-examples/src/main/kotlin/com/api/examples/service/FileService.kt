package com.api.examples.service

import com.api.examples.repository.FakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@Service
class FakeService {

    @Autowired
    lateinit var fakeRepository: FakeRepository

    fun save(file: MultipartFile) {
        for (line in BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            .lines()) {
            println(line)
        }
    }

}