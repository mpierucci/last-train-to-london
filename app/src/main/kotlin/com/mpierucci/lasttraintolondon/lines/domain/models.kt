package com.mpierucci.lasttraintolondon.lines.domain

data class Line(
    val id: String,
    val name: String,
    val statuses: List<Status>,
    val hasDisruptions: Boolean
)

data class Status(
    val id: Int,
    val severity: Int,
    val severityDescription: String
)