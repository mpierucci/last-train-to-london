package com.mpierucci.lasttraintolondon.tube.presentation

import androidx.annotation.DrawableRes

data class PresentationLineStatus(
    @DrawableRes val badgeId: Int,
    val disruptionVisibility: Int,
    val name: String,
    val status: String
)