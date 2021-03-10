package com.api.examples.repository

import com.api.examples.model.FakeModel
import org.springframework.data.repository.CrudRepository

interface FakeRepository : CrudRepository<FakeModel, Long>