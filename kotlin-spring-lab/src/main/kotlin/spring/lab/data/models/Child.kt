package spring.lab.data.models

import javax.persistence.*

@Entity
data class Child(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = -1,

    @Column(nullable = false)
    var name: String?,

    @Column(nullable = false)
    var description: String?,

    var flag: Boolean?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id")
    var relation: Relation?


) {

    constructor(
        name: String,
        description: String?,
        flag: Boolean
    ) : this(
        null,
        name,
        description,
        flag,
        null
    )

    override fun toString(): String {
        return "Child(id=$id, name=$name, description=$description, flag=$flag)"
    }
}