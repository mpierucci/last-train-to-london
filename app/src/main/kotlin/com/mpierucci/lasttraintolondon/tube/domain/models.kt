package com.mpierucci.lasttraintolondon.tube.domain

data class LineStatus(
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