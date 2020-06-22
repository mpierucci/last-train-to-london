package com.mpierucci.lasttraintolondon.lines.domain

import arrow.core.Either
import com.mpierucci.lasttraintolondon.core.failure.Failure

interface LineRepository {
    suspend fun getAll(): Either<Failure, List<Line>>
}