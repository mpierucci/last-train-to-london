package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import com.mpierucci.lasttraintolondon.lines.domain.Status
import javax.inject.Inject

class LineStatusMapper @Inject constructor() : Mapper<RestLine, Line> {

    override fun map(from: RestLine): Line {
        return Line(
            id = from.id.orEmpty(),
            name = from.name.orEmpty(),
            statuses = from.restStatuses?.map {
                mapStatus(it)
            } ?: emptyList(),
            hasDisruptions = from.disruptions?.isNotEmpty() == true
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