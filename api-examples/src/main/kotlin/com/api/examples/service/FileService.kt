package com.api.examples.service

import com.api.examples.FakeDao
import com.api.examples.component.LogExecutionTime
import com.api.examples.component.convertToFake
import com.api.examples.model.FakeModel
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
    lateinit var fakeDao: FakeDao

    @Autowired
    lateinit var fakeRepository: FakeRepository

    fun save(file: MultipartFile) {
        val modelsList: MutableList<MutableList<FakeModel>> = ArrayList()
        var modelList: MutableList<FakeModel> = ArrayList()
        var count: Int = 0

        for (line in BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            .lines()) {
            if (count++ < 200) {
                modelList.add(convertToFake(line))
            } else {
                modelsList.add(modelList)
                modelList = ArrayList()
                modelList.add(convertToFake(line))
                count = 0
            }
        }

        if (modelList.isNotEmpty()) {
            modelsList.add(modelList)
        }

        this.fakeRepository.deleteAll()
        println(":: Running thread ${Thread.currentThread().name}, total: ${modelsList.size}")
        this.saveInParallel(modelsList)
        this.fakeRepository.deleteAll()
        this.saveInSingleThread(modelsList)
    }

    @LogExecutionTime
    fun saveInParallel(modelsList: MutableList<MutableList<FakeModel>>) {
        modelsList.parallelStream().forEach {
            this.fakeDao.saveAsBatch(it)
        }
    }

    @LogExecutionTime
    fun saveInSingleThread(modelsList: MutableList<MutableList<FakeModel>>) {
        modelsList.forEach {
            this.fakeDao.saveAsBatch(it)
        }
    }

}