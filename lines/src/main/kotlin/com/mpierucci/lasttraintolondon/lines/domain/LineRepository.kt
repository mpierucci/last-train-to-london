package com.mpierucci.lasttraintolondon.lines.domain

interface LineRepository {
    suspend fun getAll(): List<Line>
}