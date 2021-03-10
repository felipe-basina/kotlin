package com.api.examples.service

import com.api.examples.dao.FakeDao
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
import java.util.concurrent.atomic.AtomicInteger

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

        // TODO: CREATE LIST IN A MORE EFFICIENT WAY
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

        println(":: Running thread ${Thread.currentThread().name}, total: ${modelsList.size}")
        this.saveInParallel(modelsList)
        this.fakeRepository.deleteAll()
        this.saveInSingleThread(modelsList)
        this.fakeRepository.deleteAll()
    }

    // TODO: CHECK ASPECT IS NOT WORKING
    @LogExecutionTime
    fun saveInParallel(modelsList: MutableList<MutableList<FakeModel>>) {
        var atomicInteger = AtomicInteger(1)
        modelsList.parallelStream().forEach {
            try {
                this.fakeDao.saveAsBatch(it, atomicInteger.incrementAndGet())
            } catch(ex: Exception) {
                println(ex.message)
            }
        }
    }

    @LogExecutionTime
    fun saveInSingleThread(modelsList: MutableList<MutableList<FakeModel>>) {
        var atomicInteger = AtomicInteger(1)
        modelsList.forEach {
            try {
                this.fakeDao.saveAsBatch(it, atomicInteger.incrementAndGet())
            } catch(ex: Exception) {
                println(ex.message)
            }
        }
    }

}