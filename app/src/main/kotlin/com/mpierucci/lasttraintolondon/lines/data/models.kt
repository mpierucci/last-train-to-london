package com.mpierucci.lasttraintolondon.lines.data

import com.google.gson.annotations.SerializedName
import com.mpierucci.lasttraintolondon.lines.domain.LineMode

data class RestLine(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("lineStatuses") val restStatuses: List<RestStatus>?,
    @SerializedName("disruptions") val disruptions: List<RestDisruption>?,
    @SerializedName("modeName") val mode: RestLineMode?
)

data class RestStatus(
    @SerializedName("id") val id: Int,
    @SerializedName("statusSeverity") val severity: Int,
    @SerializedName("statusSeverityDescription") val severityDescription: String?
)

data class RestDisruption(
    @SerializedName("category") val category: String?,
    @SerializedName("categoryDescription") val categoryDescription: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("additionalInfo") val additionalInfo: String?
)

inline class RestLineMode(val value: String)

fun RestLineMode.toMode(): LineMode {
    return when (this.value) {
        "tube" -> LineMode.Tube
        "dlr" -> LineMode.Dlr
        "overground" -> LineMode.Overground
        "bus" -> LineMode.Bus
        else -> LineMode.Undefinied
    }
}

