package com.mpierucci.lastratintolondon.usecase.rx

import com.mpierucci.lastratintolondon.usecase.Either
import com.mpierucci.lastratintolondon.usecase.failure.Failure
import io.reactivex.Single

interface SingleUseCase<REQUEST, RESULT> {
    fun execute(request: REQUEST): Single<Either<Failure, RESULT>>
}

interface SingleNoArgUseCase<RESULT> {
    fun execute(): Single<Either<Failure, RESULT>>
}
