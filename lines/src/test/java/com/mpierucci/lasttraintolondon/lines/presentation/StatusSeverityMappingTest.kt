package com.mpierucci.lasttraintolondon.lines.presentation

import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.domain.StatusSeverity
import org.junit.Assert.assertEquals
import org.junit.Test

class StatusSeverityMappingTest {
    @Test
    fun `maps good status color`() {
        val model = StatusSeverity(9).mapStatusColor()

        assertEquals(R.color.apple, model)
    }

    @Test
    fun `maps caution status color`() {
        val model = StatusSeverity(7).mapStatusColor()

        assertEquals(R.color.blazeOrange, model)
    }

    @Test
    fun `maps bad status color`() {
        val model = StatusSeverity(1).mapStatusColor()

        assertEquals(R.color.sangria, model)
    }
}