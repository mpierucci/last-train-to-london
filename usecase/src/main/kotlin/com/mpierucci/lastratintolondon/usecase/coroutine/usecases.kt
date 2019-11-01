package com.mpierucci.lastratintolondon.usecase.coroutine

import com.mpierucci.lastratintolondon.usecase.Either
import com.mpierucci.lastratintolondon.usecase.failure.Failure
import kotlinx.coroutines.flow.Flow

interface SuspendedUseCase<REQUEST, RESULT> {
    suspend fun execute(request: REQUEST): Either<Failure, RESULT>
}

interface SuspendedNoArgUseCase<RESULT> {
    suspend fun execute(): Either<Failure, RESULT>
}

interface FlowUseCase<REQUEST, RESULT> {
    suspend fun execute(request: REQUEST): Flow<Either<Failure, RESULT>>
}

interface FlowNoArgUseCase<RESULT> {
    suspend fun execute(): Flow<Either<Failure, RESULT>>
}