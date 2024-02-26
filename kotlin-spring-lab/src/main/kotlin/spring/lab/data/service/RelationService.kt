package spring.lab.data.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.lab.data.models.Fake
import spring.lab.data.models.Relation
import spring.lab.data.repository.RelationRepository
import spring.lab.data.service.MultipleEntitiesService.Companion.DELAY_IN_SECONDS
import spring.lab.data.service.MultipleEntitiesService.Companion.MILLISECONDS

@Service
class RelationService(
    @Autowired private val relationRepository: RelationRepository,
    @Autowired private val abominalService: AbominalService,
    @Autowired private val childService: ChildService
) {

    fun addRelation(fake: Fake, s: String, randomInt: Int, delay: Boolean = false) {
        var newRelation = Relation(s, randomInt, "s:::$randomInt")
        newRelation.addChild(this.childService.new(true, newRelation))
        newRelation.addChild(this.childService.new(true, newRelation))
        newRelation.addChild(this.childService.new(false, newRelation))

        val newAbominal = this.abominalService.create("Abominal+$randomInt")

        newRelation.abominal = newAbominal
        newRelation.fake = fake

        newRelation = this.relationRepository.save(newRelation)

        if (delay) {
            println("Adding delay")
            Thread.sleep(DELAY_IN_SECONDS * MILLISECONDS)
        }

        fake.addRelations(newRelation)
    }

}