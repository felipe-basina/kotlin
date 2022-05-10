package spring.lab.data.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.lab.data.models.Relation
import javax.persistence.EntityManager

@Service
class MultipleEntitiesService(@Autowired val em: EntityManager) {

    fun getMultipleEntities() {
        val fakeName = "fakiee"
        val abominalId = 9
        val abominalName = "Strange name"

        val sb = StringBuilder(" SELECT f, a, r FROM ")
            .append(" Fake f, Abominal a, Relation r ")
            .append(" WHERE ")
            .append(" f.name = :fakeName ")
            .append(" AND a.id = :abominalId ")
            .append(" AND a.name = :abominalName ")

        val resultList = em.createQuery(sb.toString())
            .setParameter("fakeName", fakeName)
            .setParameter("abominalId", abominalId.toLong())
            .setParameter("abominalName", abominalName)
            .resultList

        println(resultList)
    }

    fun getMultipleEntities2() {
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


}