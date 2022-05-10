package spring.lab.data.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spring.lab.data.models.Abominal
import spring.lab.data.models.Fake

@Repository
interface AbominalRepository : CrudRepository<Abominal, Long> {
}