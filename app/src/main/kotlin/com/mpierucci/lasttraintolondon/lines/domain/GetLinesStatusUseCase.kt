package com.mpierucci.lasttraintolondon.lines.domain


import com.mpierucci.android.architecture.usecase.UseCase
import com.mpierucci.android.architecture.usecase.failure.Failure
import com.mpierucci.android.architecture.usecase.functional.Either
import javax.inject.Inject


class GetLinesStatusUseCase @Inject constructor(
    private val repository: LineRepository
) : UseCase<Unit,List<Line>> {

    override suspend fun execute(params: Unit): Either<Failure, List<Line>> {
        //TODO warning this may catch Cancelled exceptions and let the coroutine on dubious state
        return try {
            Either.Right(repository.getAll())
        } catch (exception: Exception) {
            Either.Left(Failure.Unknown) // TODO error handling
        }
    }
}