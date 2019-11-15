package com.mpierucci.lasttraintolondon.lines.domain

import com.mpierucci.lastratintolondon.usecase.Either
import com.mpierucci.lastratintolondon.usecase.coroutine.SuspendedNoArgUseCase
import com.mpierucci.lastratintolondon.usecase.failure.Failure
import java.lang.Exception
import javax.inject.Inject


class GetLinesStatusUseCase @Inject constructor(
    private val repository: LineRepository
) : SuspendedNoArgUseCase<List<Line>> {

    override suspend fun execute(): Either<Failure, List<Line>> {
        return try {
            Either.Right(repository.getAll())
        } catch (exception: Exception) {
            Either.Left(Failure.Unknown) // TODO error handling
        }
    }
}