package com.mpierucci.lasttraintolondon.tube.data


import com.google.gson.GsonBuilder
import com.mpierucci.lasttraintolondon.loadModelFromTestFile
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class LineStatusMapperTest {

    private val mapper = LineStatusMapper()

    @Test
    fun `test map for valid gson `() {
        val lineStatus =
            mapper.map(loadModelFromTestFile(this.javaClass, "lineStatus"))



        assertEquals("bakerloo", lineStatus.id)
        assertEquals("Bakerloo", lineStatus.name)
        assertEquals(1, lineStatus.statuses.size)

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
                    .fromJson("{}", RestLineStatus::class.java)
            )
        assertEquals("", lineStatus.id)
        assertEquals("", lineStatus.name)
        Assert.assertTrue(lineStatus.statuses.isEmpty())
    }
}