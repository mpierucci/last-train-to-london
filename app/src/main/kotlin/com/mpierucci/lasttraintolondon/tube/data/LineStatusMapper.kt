package com.mpierucci.lasttraintolondon.tube.data

import com.mpierucci.lasttraintolondon.tube.domain.LineStatus
import com.mpierucci.lasttraintolondon.tube.domain.Mapper
import com.mpierucci.lasttraintolondon.tube.domain.Status
import javax.inject.Inject

class LineStatusMapper @Inject constructor() : Mapper<RestLineStatus, LineStatus> {

    override fun map(from: RestLineStatus): LineStatus {
        return LineStatus(
            id = from.id.orEmpty(),
            name = from.name.orEmpty(),
            statuses = from.restStatuses?.map {
                mapStatus(it)
            } ?: emptyList()
        )
    }

    private fun mapStatus(status: RestStatus): Status {
        return Status(
            id = status.id,
            severity = status.severity,
            severityDescription = status.severityDescription.orEmpty()
        )
    }
}