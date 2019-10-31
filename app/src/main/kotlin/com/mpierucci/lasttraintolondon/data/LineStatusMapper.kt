package com.mpierucci.lasttraintolondon.data

import javax.inject.Inject
import com.mpierucci.lasttraintolondon.domain.LineStatus as DomainLineStatus
import com.mpierucci.lasttraintolondon.domain.Status as DomainStatus

class LineStatusMapper @Inject constructor() {

    fun map(lineStatus: LineStatus): DomainLineStatus {
        return DomainLineStatus(
            id = lineStatus.id.orEmpty(),
            name = lineStatus.name.orEmpty(),
            statuses = lineStatus.statuses?.map {
                mapStatus(it)
            } ?: emptyList()
        )
    }

    private fun mapStatus(status: Status): DomainStatus {
        return DomainStatus(
            id = status.id,
            severity = status.severity,
            severityDescription = status.severityDescription.orEmpty()
        )
    }
}