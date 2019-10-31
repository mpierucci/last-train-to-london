package com.mpierucci.lastratintolondon.usecase.coroutine

import com.mpierucci.lastratintolondon.usecase.Either
import com.mpierucci.lastratintolondon.usecase.failure.Failure

interface SuspendedUseCase<REQUEST, RESULT> {
    suspend fun execute(request: REQUEST): Either<Failure, RESULT>
}

interface SuspendedNoArgUseCase<RESULT> {
    suspend fun execute(): Either<Failure, RESULT>
}