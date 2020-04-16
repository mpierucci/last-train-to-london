package com.mpierucci.lasttraintolondon.lines.data

import kotlinx.serialization.Serializable

@Serializable
data class RestLine(
    val id: String? = null,
    val name: String? = null,
    val lineStatuses: List<RestStatus>? = null,
    val modeName: String? = null //TODO https://github.com/mpierucci/last-train-to-london/issues/41
)

@Serializable
data class RestStatus(
    val id: Int = 0,
    val statusSeverity: Int = 0,
    val statusSeverityDescription: String? = null,
    val disruption: RestDisruption? = null
)

@Serializable
data class RestDisruption(
    val category: String?,
    val categoryDescription: String? = null,
    val description: String? = null,
    val summary: String? = null,
    val additionalInfo: String? = null
)

inline class RestLineMode(val value: String)





