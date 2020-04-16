package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import org.junit.Assert.assertEquals
import org.junit.Test
//TODO refactor when //TODO https://github.com/mpierucci/last-train-to-london/issues/41 it´s done
class ModeMappingTest {
    @Test
    fun `maps bus mode`() {
        val model = "bus".toMode()

        assertEquals(LineMode.Bus, model)
    }

    @Test
    fun `maps dlr mode`() {
        val model = "dlr".toMode()

        assertEquals(LineMode.Dlr, model)
    }

    @Test
    fun `maps overground mode`() {
        val model = "overground".toMode()

        assertEquals(LineMode.Overground, model)
    }

    @Test
    fun `maps tube mode`() {
        val model = "tube".toMode()

        assertEquals(LineMode.Tube, model)
    }

    @Test
    fun `maps undefined mode`() {
        val model = "nothandled".toMode()

        assertEquals(LineMode.Undefined, model)
    }
}