package spring.lab.data.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spring.lab.data.models.Abominal
import spring.lab.data.models.Fake
import java.util.*

@Repository
interface AbominalRepository : CrudRepository<Abominal, Long> {

    @EntityGraph(value = "Abominal.relations", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "SELECT a FROM Abominal a where a.id = :id")
    fun abominalByIdWithEntityGraph(id: Long): Optional<Abominal>

}