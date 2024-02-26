package spring.lab.data.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NamedEntityGraph(
    name = "Abominal.relations",
    attributeNodes = [
        NamedAttributeNode(value = "relations", subgraph = "relations")
    ],
    subgraphs = [
        NamedSubgraph(
            name = "relations",
            attributeNodes = [
                NamedAttributeNode(value = "fake")
            ]
        )
    ]
)
data class Abominal(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = -1,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var localDateTime: LocalDateTime,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "abominal")
    var relations: MutableList<Relation>

) {

    constructor(
        name: String
    ) : this(
        null,
        name,
        LocalDateTime.now(),
        mutableListOf()
    )

    fun addRelations(relation: Relation) {
        this.relations.add(relation)
    }

    override fun toString(): String {
        return "Abominal(id=$id, name=$name, localDateTime=$localDateTime)"
    }

}