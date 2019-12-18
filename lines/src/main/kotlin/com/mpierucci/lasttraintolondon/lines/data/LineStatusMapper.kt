package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Disruption
import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import com.mpierucci.lasttraintolondon.lines.domain.Status
import javax.inject.Inject

class LineStatusMapper @Inject constructor() : Mapper<RestLine, Line> {

    override fun map(from: RestLine): Line {
        return Line(
            id = from.id.orEmpty(),
            name = from.name.orEmpty(),
            statuses = from.restStatuses?.map { mapStatus(it) } ?: emptyList(),
            mode = from.mode?.toMode() ?: LineMode.Undefined
        )
    }

    private fun mapStatus(status: RestStatus): Status {
        return Status(
            id = status.id,
            severity = status.severity,
            severityDescription = status.severityDescription.orEmpty(),
            disruption = status.disruption?.let { mapDisruption(it) }
        )
    }

    private fun mapDisruption(restDisruption: RestDisruption): Disruption {
        val catDescription = restDisruption.categoryDescription.orEmpty()
        val description = restDisruption.description.orEmpty()
        val summary = restDisruption.summary.orEmpty()
        val additionalInfo = restDisruption.additionalInfo.orEmpty()

        return when (restDisruption.category) {
            "RealTime" -> Disruption.RealTime(catDescription, description, summary, additionalInfo)
            "PlannedWork" -> Disruption.PlannedWork(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            "Information" -> Disruption.Information(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            "Event" -> Disruption.Event(catDescription, description, summary, additionalInfo)
            "Crowding" -> Disruption.Crowding(catDescription, description, summary, additionalInfo)
            "StatusAlert" -> Disruption.StatusAlert(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            else -> Disruption.Undefined(catDescription, description, summary, additionalInfo)
        }
    }
}