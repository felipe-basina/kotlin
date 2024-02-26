package spring.lab.data.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spring.lab.data.models.Fake
import java.util.*

@Repository
interface FakeRepository : CrudRepository<Fake, Long> {

    @EntityGraph(value = "Fake.relations", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "SELECT f FROM Fake f where f.id = :id")
    fun fakeByIdWithEntityGraph(id: Long): Optional<Fake>

}