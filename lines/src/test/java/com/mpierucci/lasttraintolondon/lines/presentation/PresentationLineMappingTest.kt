package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.View
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.domain.*
import org.junit.Assert.assertEquals
import org.junit.Test

class PresentationLineMappingTest {
    @Test
    fun `maps to presentation line`() {
        val disruption = Disruption.Event("catDes", "Des", "Sum", "add")
        val status = Status(2, StatusSeverity(3), "des", disruption)
        val line = Line(LineId(LineIds.VICTORIA), "Victoria", LineMode.Bus, listOf(status))

        val expected = PresentationLineStatus(
            R.drawable.victoria_line_roundel, R.color.sangria,
            View.VISIBLE, "Victoria", "des"
        )

        val model = line.toPresentationModel()

        assertEquals(expected, model)
    }

    @Test
    fun `maps to presentation line without disruption`() {
        val status = Status(2, StatusSeverity(3), "des", null)
        val line = Line(LineId(LineIds.VICTORIA), "Victoria", LineMode.Bus, listOf(status))

        val expected = PresentationLineStatus(
            R.drawable.victoria_line_roundel, R.color.sangria,
            View.GONE, "Victoria", "des"
        )

        val model = line.toPresentationModel()

        assertEquals(expected, model)
    }


}