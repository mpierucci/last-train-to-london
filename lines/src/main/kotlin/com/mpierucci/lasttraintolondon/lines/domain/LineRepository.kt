package com.mpierucci.lasttraintolondon.lines.domain

import arrow.core.Either

interface LineRepository {
    suspend fun getAll(): Either<Throwable, List<Line>>
}