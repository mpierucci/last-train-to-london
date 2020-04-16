package com.mpierucci.lasttraintolondon.lines.domain

inline class LineId(val value: String)

data class Line(
    val id: LineId,
    val name: String,
    val mode: LineMode,
    val statuses: List<Status>
)

inline class StatusSeverity(val value: Int)

data class Status(
    val id: Int,
    val severity: StatusSeverity,
    val severityDescription: String,
    val disruption: Disruption?
)

sealed class LineMode {
    object Tube : LineMode()
    object Dlr : LineMode()
    object Overground : LineMode()
    object Bus : LineMode()
    object Undefined : LineMode()
}

sealed class Disruption {
    abstract val categoryDescription: String
    abstract val description: String
    abstract val summary: String
    abstract val additionalInfo: String

    data class RealTime(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class PlannedWork(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class Information(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class Event(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class Crowding(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class StatusAlert(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()

    data class Undefined(
        override val categoryDescription: String,
        override val description: String,
        override val summary: String,
        override val additionalInfo: String
    ) : Disruption()
}