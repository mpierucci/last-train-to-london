package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.View
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.LineIds
import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import com.mpierucci.lasttraintolondon.lines.domain.Status
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test

class PresentationLineMapperTest {

    private val mapper = PresentationLineStatusMapper()

    @Test
    fun `Test mapping with valid line id and no disruption`() {
        val domainModel = Line(
            id = LineIds.BAKERLOO,
            name = BAKERLOO_NAME,
            statuses = listOf(Status(0, 0, GOOD_STATUS, disruption = null)),
            mode = LineMode.Bus
        )

        val presentationModel = mapper.map(domainModel)

        assertEquals(BAKERLOO_NAME, presentationModel.name)
        assertEquals(GOOD_STATUS, presentationModel.status)
        assertEquals(View.GONE, presentationModel.disruptionVisibility)
        assertEquals(R.drawable.bakerloo_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test mapping with invalid line id and disruptions`() {
        val domainModel = Line(
            id = "rubbish",
            name = BAKERLOO_NAME,
            statuses = listOf(Status(0, 0, GOOD_STATUS, mock())),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)

        assertEquals(BAKERLOO_NAME, presentationModel.name)
        assertEquals(GOOD_STATUS, presentationModel.status)
        assertEquals(View.VISIBLE, presentationModel.disruptionVisibility)
        assertEquals(R.drawable.default_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test circle icon`() {
        val domainModel = Line(
            id = LineIds.CIRCLE,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.circle_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test central icon`() {
        val domainModel = Line(
            id = LineIds.CENTRAL,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.central_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test victoria icon`() {
        val domainModel = Line(
            id = LineIds.VICTORIA,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.victoria_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test overground icon`() {
        val domainModel = Line(
            id = LineIds.OVERGROUND,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.london_overground_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test dlr icon`() {
        val domainModel = Line(
            id = LineIds.DLR,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.dlr_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test district icon`() {
        val domainModel = Line(
            id = LineIds.DISTRICT,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.district_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test hamersmith icon`() {
        val domainModel = Line(
            id = LineIds.HAMERSMITH_CITY,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.h_and_c_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test northern icon`() {
        val domainModel = Line(
            id = LineIds.NORTHERN,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.northern_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test picadillty icon`() {
        val domainModel = Line(
            id = LineIds.PICADILLY,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.piccadilly_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test wagterloo icon`() {
        val domainModel = Line(
            id = LineIds.WATERLOO,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.w_and_c_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test jubilee icon`() {
        val domainModel = Line(
            id = LineIds.JUBILEE,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.jubilee_line_roundel, presentationModel.badgeId)
    }

    @Test
    fun `Test metropolitan icon`() {
        val domainModel = Line(
            id = LineIds.METROPOLITAN,
            name = "",
            statuses = emptyList(),
            mode = LineMode.Tube
        )

        val presentationModel = mapper.map(domainModel)
        assertEquals(R.drawable.metropolitan_line_roundel, presentationModel.badgeId)
    }

    companion object {
        private const val BAKERLOO_NAME = "Bakerloo"
        private const val GOOD_STATUS = "Good"
    }
}