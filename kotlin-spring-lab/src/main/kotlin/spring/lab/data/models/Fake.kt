package spring.lab.data.models

import javax.persistence.*

@Entity
data class Fake(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1,

    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fake")
    val relations: MutableList<Relation> = mutableListOf()

) {

    fun addRelations(relation: Relation) {
        this.relations.add(relation)
    }

    override fun toString(): String {
        return "Fake(id=$id, name=$name)"
    }

}