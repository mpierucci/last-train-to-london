package com.mpierucci.lastratintolondon.usecase.rx

interface SingleUseCase<REQUEST, RESULT> {

    fun execute(request: REQUEST): RESULT
}

interface SingleNoArgUseCase<RESULT> {

    fun execute(): RESULT

}


