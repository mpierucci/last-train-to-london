package com.mpierucci.lasttraintolondon.lines.domain

data class Line(
    val id: String,
    val name: String,
    val mode: LineMode,
    val statuses: List<Status>,
    val disruptions: List<Disruption>
)

data class Status(
    val id: Int,
    val severity: Int,
    val severityDescription: String
)

sealed class LineMode {
    object Tube : LineMode()
    object Dlr : LineMode()
    object Overground : LineMode()
    object Bus : LineMode()
    object Undefinied : LineMode()
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