package spring.lab.data.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import spring.lab.data.models.Simple
import spring.lab.data.repository.SimpleRepository

@Service
class LockTableService(@Autowired private val simpleRepository: SimpleRepository) {

    private val log: Logger = LoggerFactory.getLogger(LockTableService::class.java)

    @Transactional(propagation = Propagation.REQUIRED)
    fun saving(description: String, rollback: Boolean = false): Simple {
        val simple = Simple(description)
        this.simpleRepository.save(simple)
        if (rollback) {
            throw RuntimeException("Rolling back transaction....")
        }
        return simple
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    fun lockAndUpdate(dbId: Long, newDescription: String, delay: Long) {
        log.info("m=lockAndUpdate, ${Thread.currentThread().name}")
        var simple = this.findAndLock(dbId)
        simple.description = newDescription
        simple = this.simpleRepository.save(simple)
        log.info("m=lockAndUpdate, $simple, end")
        this.delay(delay)
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    fun update(dbId: Long, oldDescription: String, newDescription: String, delay: Long) {
        log.info("m=update, ${Thread.currentThread().name}")
        //var simple = this.findByDescription(oldDescription)
        var simple = this.simpleRepository.customFindById(dbId).get()
        simple.description = newDescription
        this.delay(delay)
        simple = this.simpleRepository.save(simple)
        log.info("m=update, $simple, end")
    }

    fun getAllSimple(): List<Simple> {
        return this.simpleRepository.findAll().toList()
    }

    fun deleteAll() {
        this.simpleRepository.deleteAll()
    }

    fun findByDescription(description: String): Simple {
        return this.simpleRepository.findByDescription(description).get()
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    fun findAndLock(dbId: Long): Simple {
        val simple = this.simpleRepository.findById(dbId).get()
        log.info("m=findAndLock, $simple")
        return simple
    }

    @Async
    fun findByIdCustom(dbId: Long): Simple {
        val simple = this.simpleRepository.customFindById(dbId).get()
        log.info("m=findAndLock, $simple")
        return simple
    }

    fun delay(delay: Long) {
        Thread.sleep(delay * 1000)
    }

}