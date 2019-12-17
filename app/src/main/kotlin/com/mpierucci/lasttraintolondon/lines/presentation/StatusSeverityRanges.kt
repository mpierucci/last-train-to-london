package com.mpierucci.lasttraintolondon.lines.presentation

@Suppress("MagicNumber")
object StatusSeverityRanges {
    val good = setOf(0, 8, 9, 10, 18, 19)
    val caution = setOf(5, 6, 7, 11, 12, 13, 14, 15, 17)
    val bad = setOf(1, 2, 3, 4, 16, 20)
}