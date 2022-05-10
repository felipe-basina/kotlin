package spring.lab.data.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Abominal(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var localDateTime: LocalDateTime = LocalDateTime.now(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "abominal")
    val relations: MutableList<Relation> = mutableListOf()

) {

    fun addRelations(relation: Relation) {
        this.relations.add(relation)
    }

    override fun toString(): String {
        return "Abominal(id=$id, name=$name, localDateTime=$localDateTime)"
    }

}