package com.mpierucci.lastratintolondon.usecase.rx

import com.mpierucci.lastratintolondon.usecase.Either
import io.reactivex.Single

interface SingleUseCase<REQUEST, RESULT> {

    fun execute(request: REQUEST): Single<Either<RESULT, Failure>>
}

interface SingleNoArgUseCase<RESULT> {
    fun execute(): Single<Either<RESULT, Failure>>
}
