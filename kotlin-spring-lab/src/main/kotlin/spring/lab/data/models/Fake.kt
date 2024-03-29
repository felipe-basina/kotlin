package spring.lab.data.models

import javax.persistence.*

@Entity
@NamedEntityGraph(
    name = "Fake.relations",
    attributeNodes = [
        NamedAttributeNode(value = "relations", subgraph = "relations")
    ],
    subgraphs = [
        NamedSubgraph(
            name = "relations",
            attributeNodes = [
                NamedAttributeNode(value = "abominal")
            ]
        )
    ]
)
data class Fake(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = -1,

    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fake")
    val relations: MutableList<Relation>

) {

    constructor(
        name: String
    ) : this(
        null,
        name,
        mutableListOf()
    )

    fun addRelations(relation: Relation) {
        this.relations.add(relation)
    }

    override fun toString(): String {
        return "Fake(id=$id, name=$name)"
    }

}