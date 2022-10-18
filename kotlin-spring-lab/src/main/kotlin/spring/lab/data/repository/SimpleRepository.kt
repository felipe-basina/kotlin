package spring.lab.data.repository

import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spring.lab.data.models.Simple
import java.util.*
import javax.persistence.LockModeType

@Repository
interface SimpleRepository : CrudRepository<Simple, Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Lock(LockModeType.PESSIMISTIC_READ)
    override fun findById(id: Long): Optional<Simple>

    fun findByDescription(description: String): Optional<Simple>

    @Query(value = "SELECT s FROM Simple s WHERE s.id = :id")
    fun customFindById(id: Long): Optional<Simple>

}