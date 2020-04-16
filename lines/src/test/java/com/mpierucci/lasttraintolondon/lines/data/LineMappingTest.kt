package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.LineId
import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import org.junit.Assert.assertEquals
import org.junit.Test

class LineMappingTest {

    @Test
    fun `maps line`() {
        val line = RestLine(id = "id", name = "name", modeName = "bus")
        val expected =
            Line(id = LineId("id"), name = "name", mode = LineMode.Bus, statuses = emptyList())

        val model = line.toDomain()

        assertEquals(expected, model)
    }
}