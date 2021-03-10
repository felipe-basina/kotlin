package com.api.examples.component

import com.api.examples.model.FakeModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeFormatters {
    const val DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMATTER = "yyyy-MM-dd"
}

fun convertToFake(contentAsString: String): FakeModel {
    val splitContent = contentAsString.split("|")
    return FakeModel(null,
               splitContent[1].trim(),
               splitContent[2].trim(),
               LocalDateTime.parse(splitContent[3].trim(), DateTimeFormatter.ofPattern(DateTimeFormatters.DATE_TIME_FORMATTER)),
               LocalDate.parse(splitContent[4].trim(), DateTimeFormatter.ofPattern(DateTimeFormatters.DATE_FORMATTER)),
        "1" == splitContent[5].trim())
}