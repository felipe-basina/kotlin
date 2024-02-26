package spring.lab.data.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import spring.lab.data.models.Abominal
import spring.lab.data.repository.AbominalRepository

@Service
class AbominalService(
    @Autowired private val abominalRepository: AbominalRepository
) {

    @Transactional(propagation = Propagation.REQUIRED)
    fun create(name: String): Abominal {
        val abominal = Abominal(name)
        return this.abominalRepository.save(abominal)
    }

}