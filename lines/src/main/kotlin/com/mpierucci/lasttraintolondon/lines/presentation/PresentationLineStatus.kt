package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class PresentationLineStatus(
    @DrawableRes val badgeId: Int,
    @ColorRes val statusColor: Int,
    val disruptionVisibility: Int,
    val name: String,
    val status: String
)