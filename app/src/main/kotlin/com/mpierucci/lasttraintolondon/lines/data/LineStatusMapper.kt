package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.*
import javax.inject.Inject

class LineStatusMapper @Inject constructor() : Mapper<RestLine, Line> {

    override fun map(from: RestLine): Line {
        return Line(
            id = from.id.orEmpty(),
            name = from.name.orEmpty(),
            statuses = from.restStatuses?.map { mapStatus(it) } ?: emptyList(),
            mode = from.mode?.toMode() ?: LineMode.Undefinied,
            disruptions = from.disruptions?.map { mapDisruption(it) } ?: emptyList()

        )
    }

    private fun mapStatus(status: RestStatus): Status {
        return Status(
            id = status.id,
            severity = status.severity,
            severityDescription = status.severityDescription.orEmpty()
        )
    }

    private fun mapDisruption(restDisruption: RestDisruption): Disruption {
        val catDescription = restDisruption.categoryDescription.orEmpty()
        val description = restDisruption.description.orEmpty()
        val summary = restDisruption.summary.orEmpty()
        val additionalInfo = restDisruption.additionalInfo.orEmpty()

        return when (restDisruption.category) {
            "RealTime" -> Disruption.RealTime(catDescription, description, summary, additionalInfo)
            "PlannedWork" -> Disruption.RealTime(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            "Information" -> Disruption.RealTime(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            "Event" -> Disruption.RealTime(catDescription, description, summary, additionalInfo)
            "Crowding" -> Disruption.RealTime(catDescription, description, summary, additionalInfo)
            "StatusAlert" -> Disruption.RealTime(
                catDescription,
                description,
                summary,
                additionalInfo
            )
            else -> Disruption.RealTime(catDescription, description, summary, additionalInfo)
        }
    }
}