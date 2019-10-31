package com.mpierucci.lasttraintolondon.data

import com.google.gson.annotations.SerializedName

data class LineStatus(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("lineStatuses") val statuses: List<Status>?
)

data class Status(
    @SerializedName("id") val id: Int,
    @SerializedName("statusSeverity") val severity: Int,
    @SerializedName("statusSeverityDescription") val severityDescription: String?
)