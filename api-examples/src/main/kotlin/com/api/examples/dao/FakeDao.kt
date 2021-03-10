package com.api.examples.dao

import com.api.examples.model.FakeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.sql.Timestamp

@Repository
class FakeDao {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = [ Exception::class ])
    fun saveAsBatch(fakeList: MutableList<FakeModel>, sentinel: Int) {
        println("Running thread ${Thread.currentThread().name}, total: ${fakeList.size}")

        this.jdbcTemplate
            .batchUpdate("INSERT INTO fakeone (description, code, creation_date, business_date, active) values (?, ?, ?, ?, ?)",
                fakeList, fakeList.size) { ps, (_, description, code, creationDate, businessDate, active) ->
                ps.setString(1, description)
                ps.setString(2, code)
                ps.setTimestamp(3, Timestamp.valueOf(creationDate))
                ps.setDate(4, Date.valueOf(businessDate))
                ps.setBoolean(5, active)
            }

//        if (sentinel % 2 == 0) {
//            throw Exception("Total objects not saved in database=${fakeList.size} " +
//                    "| thread=${Thread.currentThread().name} " +
//                    "| sentinel=$sentinel")
//        }
    }

}