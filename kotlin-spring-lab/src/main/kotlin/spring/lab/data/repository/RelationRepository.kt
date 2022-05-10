package spring.lab.data.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spring.lab.data.models.Relation

@Repository
interface RelationRepository : CrudRepository<Relation, Long> {
}