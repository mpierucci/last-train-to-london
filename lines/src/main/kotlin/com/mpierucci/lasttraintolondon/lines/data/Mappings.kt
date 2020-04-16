package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.*
import timber.log.Timber


internal fun RestLine.toDomain(): Line {
    return Line(
        id = LineId(id.orEmpty()),
        name = name.orEmpty(),
        statuses = lineStatuses?.map { it.toDomain() } ?: emptyList(),
        mode = modeName?.toMode() ?: LineMode.Undefined
    )
}


internal fun RestStatus.toDomain(): Status {
    return Status(
        id = id,
        severity = StatusSeverity(statusSeverity),
        severityDescription = statusSeverityDescription.orEmpty(),
        disruption = disruption?.toDomain()
    )
}


internal fun RestDisruption.toDomain(): Disruption {
    val catDescription = categoryDescription.orEmpty()
    val description = description.orEmpty()
    val summary = summary.orEmpty()
    val additionalInfo = additionalInfo.orEmpty()

    return when (category) {
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
        else -> {
            Timber.w("Unhandled line mode processed: $this")
            Disruption.Undefined(catDescription, description, summary, additionalInfo)
        }
    }
}


//TODO https://github.com/mpierucci/last-train-to-london/issues/41
internal fun RestLineMode.toMode(): LineMode {
    return when (this.value) {
        "tube" -> LineMode.Tube
        "dlr" -> LineMode.Dlr
        "overground" -> LineMode.Overground
        "bus" -> LineMode.Bus
        else -> {
            Timber.w("Unhandled disruption category processed: $this")
            LineMode.Undefined
        }
    }
}

internal fun String.toMode(): LineMode {
    return when (this) {
        "tube" -> LineMode.Tube
        "dlr" -> LineMode.Dlr
        "overground" -> LineMode.Overground
        "bus" -> LineMode.Bus
        else -> {
            Timber.w("Unhandled line mode processed: $this")
            LineMode.Undefined
        }
    }
}