package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Disruption
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DisruptionMappingTest {

    @Test
    fun `maps disruption values`() {
        val disruption = RestDisruption(
            category = "Event",
            categoryDescription = "catDes",
            additionalInfo = "additionalInfo",
            summary = "summary",
            description = "description"
        )

        val expected = Disruption.Event(
            categoryDescription = "catDes",
            additionalInfo = "additionalInfo",
            summary = "summary",
            description = "description"
        )

        val model = disruption.toDomain()

        assertEquals(expected, model)
    }

    @Test
    fun `maps information disruption`() {
        val disruption = RestDisruption(category = "Information")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.Information)
    }

    @Test
    fun `maps planned work disruption`() {
        val disruption = RestDisruption(category = "PlannedWork")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.PlannedWork)
    }

    @Test
    fun `maps crowding disruption`() {
        val disruption = RestDisruption(category = "Crowding")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.Crowding)
    }

    @Test
    fun `maps status alert disruption`() {
        val disruption = RestDisruption(category = "StatusAlert")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.StatusAlert)
    }

    @Test
    fun `maps real time disruption`() {
        val disruption = RestDisruption(category = "RealTime")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.RealTime)
    }


    @Test
    fun `maps undefined disruption`() {
        val disruption = RestDisruption(category = "undefined")

        val model = disruption.toDomain()

        assertTrue(model is Disruption.Undefined)
    }
}