package com.mpierucci.lasttraintolondon.tube.data

import com.google.gson.annotations.SerializedName

data class RestLineStatus(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("lineStatuses") val restStatuses: List<RestStatus>?,
    @SerializedName("disruptions") val disruptions: List<Unit>?
)

data class RestStatus(
    @SerializedName("id") val id: Int,
    @SerializedName("statusSeverity") val severity: Int,
    @SerializedName("statusSeverityDescription") val severityDescription: String?
)