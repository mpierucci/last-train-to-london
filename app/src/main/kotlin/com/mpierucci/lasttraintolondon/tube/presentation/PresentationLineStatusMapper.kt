package com.mpierucci.lasttraintolondon.tube.presentation

import android.view.View
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.tube.domain.LineIds
import com.mpierucci.lasttraintolondon.tube.domain.LineStatus
import com.mpierucci.lasttraintolondon.tube.domain.Mapper

class PresentationLineStatusMapper : Mapper<LineStatus, PresentationLineStatus> {
    override fun map(from: LineStatus): PresentationLineStatus {
        return PresentationLineStatus(
            badgeId = mapBadgeResource(from.id),
            statusColor = R.color.district,
            name = from.name,
            status = from.statuses.firstOrNull()?.severityDescription.orEmpty(),
            disruptionVisibility = if (from.hasDisruptions) View.VISIBLE else View.GONE
        )
    }

    private fun mapBadgeResource(lineId: String): Int {
        return when (lineId) {
            LineIds.CENTRAL -> R.drawable.central_line_roundel
            LineIds.PICADILLY -> R.drawable.piccadilly_line_roundel
            LineIds.VICTORIA -> R.drawable.victoria_line_roundel
            LineIds.NORTHERN -> R.drawable.northern_line_roundel
            LineIds.DISTRICT -> R.drawable.district_line_roundel
            LineIds.CIRCLE -> R.drawable.circle_line_roundel
            LineIds.HAMERSMITH_CITY -> R.drawable.h_and_c_line_roundel
            LineIds.METROPOLITAN -> R.drawable.metropolitan_line_roundel
            LineIds.BAKERLOO -> R.drawable.bakerloo_line_roundel
            LineIds.WATERLOO -> R.drawable.w_and_c_line_roundel
            LineIds.JUBILEE -> R.drawable.jubilee_line_roundel
            LineIds.OVERGROUND -> R.drawable.london_overground_roundel
            else -> R.drawable.default_roundel
        }
    }
}