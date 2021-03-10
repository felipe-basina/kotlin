package com.api.examples.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "fakeone")
data class FakeModel(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                        val description: String,
                        val code: String,
                        val creationDate: LocalDateTime,
                        val businessDate: LocalDate,
                        val active: Boolean)