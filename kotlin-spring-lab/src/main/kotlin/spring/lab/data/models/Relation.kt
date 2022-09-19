package spring.lab.data.models

import javax.persistence.*

@Entity
data class Relation(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = -1,

    @Column(nullable = false)
    var name: String?,

    @Column(nullable = false)
    var identifier: Int?,

    @Column(nullable = false)
    var description: String?,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "relation", cascade = [CascadeType.ALL])
    val childs: MutableList<Child>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fake_id")
    var fake: Fake?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abominal_id")
    var abominal: Abominal?

) {

    constructor(
        name: String,
        identifier: Int,
        description: String,
    ) : this(
        null,
        name,
        identifier,
        description,
        mutableListOf(),
        null,
        null
    )

    fun addChild(child: Child) {
        this.childs.add(child)
    }

    override fun toString(): String {
        return "Relation(id=$id, name=$name, identifier=$identifier, description=$description, childs=$childs)"
    }

}