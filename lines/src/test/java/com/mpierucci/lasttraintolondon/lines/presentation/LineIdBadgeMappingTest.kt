package com.mpierucci.lasttraintolondon.lines.presentation

import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.domain.LineId
import com.mpierucci.lasttraintolondon.lines.domain.LineIds
import org.junit.Assert.assertEquals
import org.junit.Test

class LineIdBadgeMappingTest {
    @Test
    fun `maps central line icon resource`() {
        val model = LineId(LineIds.CENTRAL).toIconResource()

        assertEquals(R.drawable.central_line_roundel, model)
    }

    @Test
    fun `maps bakerloo line icon resource`() {
        val model = LineId(LineIds.BAKERLOO).toIconResource()

        assertEquals(R.drawable.bakerloo_line_roundel, model)
    }

    @Test
    fun `maps jubilee line icon resource`() {
        val model = LineId(LineIds.JUBILEE).toIconResource()

        assertEquals(R.drawable.jubilee_line_roundel, model)
    }

    @Test
    fun `maps dlr line icon resource`() {
        val model = LineId(LineIds.DLR).toIconResource()

        assertEquals(R.drawable.dlr_roundel, model)
    }


    @Test
    fun `maps district line icon resource`() {
        val model = LineId(LineIds.DISTRICT).toIconResource()

        assertEquals(R.drawable.district_line_roundel, model)
    }

    @Test
    fun `maps overground line icon resource`() {
        val model = LineId(LineIds.OVERGROUND).toIconResource()

        assertEquals(R.drawable.london_overground_roundel, model)
    }

    @Test
    fun `maps northern line icon resource`() {
        val model = LineId(LineIds.NORTHERN).toIconResource()

        assertEquals(R.drawable.northern_line_roundel, model)
    }

    @Test
    fun `maps metropolitan line icon resource`() {
        val model = LineId(LineIds.METROPOLITAN).toIconResource()

        assertEquals(R.drawable.metropolitan_line_roundel, model)
    }

    @Test
    fun `maps hasmersmith line icon resource`() {
        val model = LineId(LineIds.HAMERSMITH_CITY).toIconResource()

        assertEquals(R.drawable.h_and_c_line_roundel, model)
    }

    @Test
    fun `maps circle line icon resource`() {
        val model = LineId(LineIds.CIRCLE).toIconResource()

        assertEquals(R.drawable.circle_line_roundel, model)
    }

    @Test
    fun `maps waterloo line icon resource`() {
        val model = LineId(LineIds.WATERLOO).toIconResource()

        assertEquals(R.drawable.w_and_c_line_roundel, model)
    }

    @Test
    fun `maps picadilly line icon resource`() {
        val model = LineId(LineIds.PICADILLY).toIconResource()

        assertEquals(R.drawable.piccadilly_line_roundel, model)
    }

    @Test
    fun `maps victoria line icon resource`() {
        val model = LineId(LineIds.VICTORIA).toIconResource()

        assertEquals(R.drawable.victoria_line_roundel, model)
    }

    @Test
    fun `maps unhandled line icon resource`() {
        val model = LineId("line").toIconResource()

        assertEquals(R.drawable.default_roundel, model)
    }
}