package com.mpierucci.lasttraintolondon.lines.data

import com.google.gson.GsonBuilder
import com.mpierucci.lasttraintolondon.loadModelFromTestFile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class LineMapperTest {

    private val mapper = LineStatusMapper()

    @Test
    fun `test map for valid gson `() {
        val lineStatus =
            mapper.map(loadModelFromTestFile(this.javaClass, "lineStatus"))

        assertEquals("bakerloo", lineStatus.id)
        assertEquals("Bakerloo", lineStatus.name)
        assertEquals(1, lineStatus.statuses.size)
        assertEquals(true, lineStatus.hasDisruptions)

        val status = lineStatus.statuses.firstOrNull()

        assertEquals(0, status?.id)
        assertEquals(10, status?.severity)
        assertEquals("Good Service", status?.severityDescription)
    }

    @Test
    fun `test map for empty values`() {
        val lineStatus =
            mapper.map(
                GsonBuilder()
                    .create()
                    .fromJson("{}", RestLine::class.java)
            )
        assertEquals("", lineStatus.id)
        assertEquals("", lineStatus.name)
        assertTrue(lineStatus.statuses.isEmpty())
        assertFalse(lineStatus.hasDisruptions)
    }
}