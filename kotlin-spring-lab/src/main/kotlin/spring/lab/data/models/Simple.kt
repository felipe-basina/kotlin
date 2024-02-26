package spring.lab.data.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Simple(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = -1,

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var localDateTime: LocalDateTime,

    @Column(nullable = false)
    var counter: Long,

    ) {

    @PreUpdate
    fun update() {
        this.localDateTime = LocalDateTime.now()
    }

    override fun toString(): String {
        return "Simple(id=$id, description=$description, localDateTime=$localDateTime, counter=$counter)"
    }

    constructor(
        name: String,
        counter: Long
    ) : this(
        null,
        name,
        LocalDateTime.now(),
        counter
    )

}