package com.api.examples.service

import com.api.examples.FakeDao
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

        modelsList.forEach {
            this.fakeDao.saveAsBatch(it)
        }
    }

}