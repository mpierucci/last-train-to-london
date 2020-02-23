package com.mpierucci.lasttraintolondon.lines.domain

import com.mpierucci.android.architecture.usecase.failure.Failure
import com.mpierucci.android.architecture.usecase.functional.Either
import kotlinx.coroutines.flow.Flow

interface LineRepository {

    fun getAll(): Flow<Either<Failure,List<Line>>>

    fun retry()
}