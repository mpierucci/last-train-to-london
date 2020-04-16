package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Status
import com.mpierucci.lasttraintolondon.lines.domain.StatusSeverity
import org.junit.Assert.assertEquals
import org.junit.Test

class StatusMappingTest {

    @Test
    fun `maps status`() {
        val status = RestStatus(statusSeverity = 3, statusSeverityDescription = "des", id = 4)
        val expected = Status(
            severity = StatusSeverity(3),
            severityDescription = "des",
            id = 4,
            disruption = null
        )

        val model = status.toDomain()

        assertEquals(expected, model)
    }
}