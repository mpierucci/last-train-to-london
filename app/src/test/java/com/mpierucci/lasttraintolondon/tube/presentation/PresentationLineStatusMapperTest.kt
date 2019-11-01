package com.mpierucci.lasttraintolondon.tube.presentation

import android.view.View
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.tube.domain.LineIds
import com.mpierucci.lasttraintolondon.tube.domain.LineStatus
import com.mpierucci.lasttraintolondon.tube.domain.Status
import org.junit.Assert.assertEquals
import org.junit.Test

class PresentationLineStatusMapperTest {

    private val mapper = PresentationLineStatusMapper()

    @Test
    fun `Test mapping with valid line id and no disruption`() {
        val domainModel = LineStatus(
            id = LineIds.BAKERLOO,
            name = BAKERLOO_NAME,
            hasDisruptions = false,
            statuses = listOf(Status(0, 0, GOOD_STATUS))
        )

        val presentationModel = mapper.map(domainModel)

        assertEquals(BAKERLOO_NAME, presentationModel.name)
        assertEquals(GOOD_STATUS, presentationModel.status)
        assertEquals(View.GONE, presentationModel.disruptionVisibility)
        assertEquals(R.drawable.bakerloo_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test mapping with invalid line id and disruptions`() {
        val domainModel = LineStatus(
            id = "rubbish",
            name = BAKERLOO_NAME,
            hasDisruptions = true,
            statuses = listOf(Status(0, 0, GOOD_STATUS))
        )

        val presentationModel = mapper.map(domainModel)

        assertEquals(BAKERLOO_NAME, presentationModel.name)
        assertEquals(GOOD_STATUS, presentationModel.status)
        assertEquals(View.VISIBLE, presentationModel.disruptionVisibility)
        assertEquals(R.drawable.default_roundel, presentationModel.badgeId)
    }

    companion object {
        private const val BAKERLOO_NAME = "Bakerloo"
        private const val GOOD_STATUS = "Good"
    }
}