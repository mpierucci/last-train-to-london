package com.mpierucci.lasttraintolondon.lines.data

import com.google.gson.GsonBuilder
import com.mpierucci.lasttraintolondon.lines.domain.Disruption
import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import com.mpierucci.lasttraintolondon.loadModelFromTestFile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LineMapperTest {

    private val mapper = LineStatusMapper()
    lateinit var rawLines: List<RestLine>

    @Before
    fun setUp() {
        rawLines = loadModelFromTestFile(this.javaClass, "lineStatus")

    }

    @Test
    fun `test tube mode no status disruption`() {
        val lineStatus = mapper.map(rawLines[0])

        assertEquals("bakerloo", lineStatus.id)
        assertEquals("Bakerloo", lineStatus.name)
        assertEquals(1, lineStatus.statuses.size)
        assertEquals(LineMode.Tube, lineStatus.mode)

        val status = lineStatus.statuses.firstOrNull()

        assertEquals(0, status?.id)
        assertEquals(10, status?.severity)
        assertEquals("Good Service", status?.severityDescription)
        assertNull(status?.disruption)

    }

    @Test
    fun `test tube mode, Planned Closure disruption`() {
        val lineStatus = mapper.map(rawLines[2])

        assertEquals("circle", lineStatus.id)
        assertEquals("Circle", lineStatus.name)
        assertEquals(1, lineStatus.statuses.size)
        assertEquals(LineMode.Tube, lineStatus.mode)

        val status = lineStatus.statuses.firstOrNull()

        assertEquals(0, status?.id)
        assertEquals(4, status?.severity)
        assertEquals("Planned Closure", status?.severityDescription)

        val disruption = status?.disruption

        assertEquals("PlannedWork", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, " +
                    "no Circle Line service. A special combined District and Hammersmith " +
                    "& City line service will operate between Upminster and Hammersmith " +
                    "(Hammersmith & City line station) via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("fakeSummary", disruption?.summary)
        assertEquals("fakeInfo", disruption?.additionalInfo)

        assertTrue(disruption is Disruption.PlannedWork)
    }

    @Test
    fun `test tube mode, Part Closure disruption`() {
        val lineStatus = mapper.map(rawLines[3])

        val status = lineStatus.statuses.firstOrNull()
        assertEquals("Part Closure", status?.severityDescription)

        val disruption = status?.disruption

        assertEquals("PlannedWork", disruption?.categoryDescription)
        assertEquals(
            "District Line: Sunday 3 November, no service between Earl's Court and Aldgate East. A reduced service will operate between Earl's Court and Ealing Broadway. A special combined District and Hammersmith & City Line service will operate between Upminster and Hammersmith (Hammersmith & Citry line station) via King's Cross St. Pancras,",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.PlannedWork)
    }

    @Test
    fun `test overground mode, Event disruption`() {
        val lineStatus = mapper.map(rawLines[7])

        assertEquals(LineMode.Overground, lineStatus.mode)

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals(
            "LONDON OVERGROUND: Sunday 3 November, no service between Surrey Quays and " +
                    "Clapham Junction. Replacement " +
                    "buses operate between Canada Water and Clapham Junction.",
            disruption?.description
        )
        assertEquals("Event", disruption?.categoryDescription)
        assertEquals("", disruption?.summary)
        assertEquals(
            "Replacement buses operateService M: Canada Water - Surrey Quays - Queens " +
                    "Road Peckham - Peckham Rye - Denmark Hill - Clapham High Street - Wandsworth" +
                    " Road - Clapham Junction.",
            disruption?.additionalInfo
        )
        assertTrue(disruption is Disruption.Event)
    }

    @Test
    fun `test dlr mode, Information disruption`() {
        val lineStatus = mapper.map(rawLines[4])

        assertEquals(LineMode.Dlr, lineStatus.mode)

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals("Information", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, no Circle Line service. " +
                    "A special combined District and Hammersmith & City line service will operate" +
                    " between Upminster and Hammersmith (Hammersmith & City line station) " +
                    "via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.Information)
    }

    @Test
    fun `test bus mode, Information disruption`() {
        val lineStatus = mapper.map(rawLines[5])

        assertEquals(LineMode.Bus, lineStatus.mode)

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals("RealTime", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, no Circle Line service. " +
                    "A special combined District and Hammersmith & City line service will operate" +
                    " between Upminster and Hammersmith (Hammersmith & City line station) " +
                    "via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.RealTime)
    }


    @Test
    fun `test Crowding disruption`() {
        val lineStatus = mapper.map(rawLines[6])

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals("Crowding", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, no Circle Line service. " +
                    "A special combined District and Hammersmith & City line service will operate " +
                    "between Upminster and Hammersmith (Hammersmith & City line station) " +
                    "via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.Crowding)
    }

    @Test
    fun `test StatusAlert disruption`() {
        val lineStatus = mapper.map(rawLines[8])

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals("StatusAlert", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, no Circle Line service. " +
                    "A special combined District and Hammersmith & City line service will" +
                    " operate between Upminster and Hammersmith (Hammersmith & City line station)" +
                    " via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.StatusAlert)
    }

    @Test
    fun `test Undefined disruption`() {
        val lineStatus = mapper.map(rawLines[9])

        val status = lineStatus.statuses.firstOrNull()

        val disruption = status?.disruption

        assertEquals("Undefined", disruption?.categoryDescription)
        assertEquals(
            "Circle Line: Saturday 2 and Sunday 3 November, no Circle Line service." +
                    " A special combined District and Hammersmith & City line service will operate" +
                    " between Upminster and Hammersmith (Hammersmith & City line station) " +
                    "via King's Cross St. Pancras.",
            disruption?.description
        )
        assertEquals("", disruption?.summary)
        assertEquals("", disruption?.additionalInfo)
        assertTrue(disruption is Disruption.Undefined)
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
        assertEquals(LineMode.Undefined, lineStatus.mode)
    }
}