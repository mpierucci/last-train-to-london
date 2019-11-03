package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.annotation.DrawableRes

data class PresentationLineStatus(
    @DrawableRes val badgeId: Int,
    val disruptionVisibility: Int,
    val name: String,
    val status: String
)