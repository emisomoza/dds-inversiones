package ar.edu.utn.dds.utils.date

import java.time.Instant
import java.time.LocalDate

class DateTimeUtils {

    static LocalDate convert(Date date) {
        return LocalDate.from(date.toInstant())
    }

    static Date convert(LocalDate localDate) {
        return Date.from(Instant.from(localDate))
    }
}
