package com.mpierucci.lastratintolondon.usecase.rx

import io.reactivex.Single

interface SingleUseCase<REQUEST, RESULT> {

    fun execute(request: REQUEST): Single<Result<RESULT>>
}

interface SingleNoArgUseCase<RESULT> {
    fun execute(): Single<Result<RESULT>>
}
