package com.mpierucci.lasttraintolondon.domain

data class LineStatus(val id: String, val name: String, val statuses: List<Status>)

data class Status(val id: Int, val severity: Int, val severityDescription: String)