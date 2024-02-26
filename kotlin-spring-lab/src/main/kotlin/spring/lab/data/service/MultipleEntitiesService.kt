package spring.lab.data.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import spring.lab.data.models.Relation
import spring.lab.data.repository.AbominalRepository
import spring.lab.data.repository.FakeRepository
import javax.persistence.EntityManager
import kotlin.random.Random

@Service
class MultipleEntitiesService(
    @Autowired private val em: EntityManager,
    @Autowired private val fakeRepository: FakeRepository,
    @Autowired private val abominalRepository: AbominalRepository,
    @Autowired private val relationService: RelationService
) {

    fun getMultipleEntities() {
        val fakeName = "fakiee"
        val abominalId = 9
        val abominalName = "Strange name"

        val sb = StringBuilder(" SELECT DISTINCT r FROM ")
            .append(" Relation r ")
            .append(" JOIN FETCH r.fake f ")
            .append(" JOIN FETCH r.abominal a ")
            .append(" JOIN FETCH r.childs ")
            .append(" WHERE ")
            .append(" f.name = :fakeName ")
            .append(" AND a.id = :abominalId ")
            .append(" AND a.name = :abominalName ")

        val resultList = em.createQuery(sb.toString(), Relation::class.java)
            .setParameter("fakeName", fakeName)
            .setParameter("abominalId", abominalId.toLong())
            .setParameter("abominalName", abominalName)
            .resultList

        println("\n\n")

        val singleRelation = resultList[0]
        println("Relation = $singleRelation\nChilds = ${singleRelation.childs}\nFake = ${singleRelation.fake}\nAbominal = ${singleRelation.abominal}")
        singleRelation.childs.forEach { println(it) }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    fun getFakeById(fakeId: Long) {
//        println("[WITHOUT Entity Graphs]")
//        val optionalFakeWithoutEG = this.fakeRepository.findById(fakeId)
//        println("[WITHOUT Entity Graphs] Exists fake with id=$fakeId? ${optionalFakeWithoutEG.isPresent}")
//        val fakeWithoutEG = optionalFakeWithoutEG.get()
//        println("[WITHOUT Entity Graphs] Total relations found ${fakeWithoutEG.relations.size}")
//        println("[WITHOUT Entity Graphs] Total children for relation found ${fakeWithoutEG.relations[0].childs.size}")

        println("[WITH Entity Graphs]")
        val optionalFakeWithEG = this.fakeRepository.fakeByIdWithEntityGraph(fakeId)
        println("[WITH Entity Graphs] Exists fake with id=$fakeId? ${optionalFakeWithEG.isPresent}")
        val fakeWithEG = optionalFakeWithEG.get()
        println("[WITH Entity Graphs] Total relations found ${fakeWithEG.relations.size}")

        println("[WITH Entity Graphs]")
        //val abominalId = fakeWithEG.relations[0].abominal!!.id!!
        val abominalId = 7L
        val optionalAbominalWithEG = this.abominalRepository.abominalByIdWithEntityGraph(abominalId)
        println("[WITH Entity Graphs] Exists abominal with id=$abominalId? ${optionalAbominalWithEG.isPresent}")
        val abominalWithEG = optionalAbominalWithEG.get()
        println("[WITH Entity Graphs] Total relations found ${abominalWithEG.relations.size}")
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = DELAY_IN_SECONDS)
//    @Transactional(propagation = Propagation.REQUIRED)
    fun addIntoFake(fakeId: Long, delay: Boolean = false) {
        val initTime = System.currentTimeMillis()
        try {
            val optionalFake = this.fakeRepository.findById(fakeId)
            println("Exists fake with id=$fakeId? ${optionalFake.isPresent}")

            val fake = optionalFake.get()
            val randomInt = Random(100).nextInt(1, 99)
            this.relationService.addRelation(fake, "Another Relation_${randomInt}", randomInt, delay)
            this.fakeRepository.save(fake)
        } catch (ex: Throwable) {
            ex.printStackTrace()
        } finally {
            val elapsedTime = (System.currentTimeMillis() - initTime)/1000
            println("Elapsed time = $elapsedTime(s)")
        }
    }

    companion object {
        const val DELAY_IN_SECONDS = 4
        const val MILLISECONDS = 1000L
    }

}