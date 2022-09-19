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

    ) {

    @PreUpdate
    fun update() {
        this.localDateTime = LocalDateTime.now()
    }

    constructor(
        name: String
    ) : this(
        null,
        name,
        LocalDateTime.now()
    )

    override fun toString(): String {
        return "Simple(" +
                "id=$id, " +
                "description=$description, " +
                "localDateTime=$localDateTime" +
                ")"
    }

}