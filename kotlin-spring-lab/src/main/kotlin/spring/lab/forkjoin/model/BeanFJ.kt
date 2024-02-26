package spring.lab.forkjoin.model

import org.slf4j.LoggerFactory

data class BeanFJ(
    var id: String,
    var code: Long,
) {

    private val log = LoggerFactory.getLogger(BeanFJ::class.java)

    private var original: Long = this.code
    private var increment: Long = -1L

    fun incrementBy(incrementValue: Long) {
        this.increment = incrementValue
        this.code = code * incrementValue
    }

    fun status() {
        log.info("original $original | incremented (* ${this.increment}) ${this.code}")
    }

    fun printContent(): String {
        return "$original;${this.increment};${this.code}\n"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BeanFJ

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}