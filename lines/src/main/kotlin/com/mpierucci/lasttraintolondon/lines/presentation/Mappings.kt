package com.mpierucci.lasttraintolondon.lines.presentation

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.LineId
import com.mpierucci.lasttraintolondon.lines.domain.LineIds
import com.mpierucci.lasttraintolondon.lines.domain.StatusSeverity

internal fun Line.toPresentationModel(): PresentationLineStatus {
    val topStatus = statuses.firstOrNull()
    return PresentationLineStatus(
        badgeId = id.toIconResource(),
        name = name,
        status = topStatus?.severityDescription.orEmpty(),
        disruptionVisibility = if (topStatus?.disruption != null) View.VISIBLE else View.GONE,
        statusColor = topStatus?.severity?.mapStatusColor() ?: 0
    )
}


@ColorRes
internal fun StatusSeverity.mapStatusColor(): Int {
    return when (this.value) {
        in StatusSeverityRanges.good -> R.color.apple
        in StatusSeverityRanges.caution -> R.color.blazeOrange
        in StatusSeverityRanges.bad -> R.color.sangria
        else -> 0
    }
}

@DrawableRes
internal fun LineId.toIconResource(): Int {
    return when (this.value) {
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
        LineIds.DLR -> R.drawable.dlr_roundel
        else -> R.drawable.default_roundel
    }
}