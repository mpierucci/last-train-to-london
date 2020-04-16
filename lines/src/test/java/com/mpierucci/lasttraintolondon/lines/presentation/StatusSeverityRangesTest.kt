package com.mpierucci.lasttraintolondon.lines.presentation

import org.junit.Assert.assertTrue
import org.junit.Test

class StatusSeverityRangesTest {

    @Test
    fun getGood() {

        assertTrue(StatusSeverityRanges.good.contains(0))
        assertTrue(StatusSeverityRanges.good.contains(8))
        assertTrue(StatusSeverityRanges.good.contains(9))
        assertTrue(StatusSeverityRanges.good.contains(10))
        assertTrue(StatusSeverityRanges.good.contains(18))
        assertTrue(StatusSeverityRanges.good.contains(19))
    }

    @Test
    fun getCaution() {
        assertTrue(StatusSeverityRanges.caution.contains(5))
        assertTrue(StatusSeverityRanges.caution.contains(6))
        assertTrue(StatusSeverityRanges.caution.contains(7))
        assertTrue(StatusSeverityRanges.caution.contains(11))
        assertTrue(StatusSeverityRanges.caution.contains(12))
        assertTrue(StatusSeverityRanges.caution.contains(13))
        assertTrue(StatusSeverityRanges.caution.contains(13))
        assertTrue(StatusSeverityRanges.caution.contains(14))
        assertTrue(StatusSeverityRanges.caution.contains(15))
        assertTrue(StatusSeverityRanges.caution.contains(17))
    }

    @Test
    fun getBad() {
        assertTrue(StatusSeverityRanges.bad.contains(1))
        assertTrue(StatusSeverityRanges.bad.contains(2))
        assertTrue(StatusSeverityRanges.bad.contains(3))
        assertTrue(StatusSeverityRanges.bad.contains(4))
        assertTrue(StatusSeverityRanges.bad.contains(16))
        assertTrue(StatusSeverityRanges.bad.contains(20))
    }
}