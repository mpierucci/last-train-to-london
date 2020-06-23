package com.mpierucci.lasttraintolondon.core.failure

import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

interface FailureHandler {
    fun getFailure(throwable: Throwable): Failure
}

class DefaultFailureHandler @Inject constructor() : FailureHandler {
    override fun getFailure(throwable: Throwable): Failure {
        return when (throwable) {
            is HttpException -> getFailureForHttpException(throwable)
            is IOException -> Failure.NetworkConnection
            else -> {
                Timber.e(throwable, "Unknown Error")
                Failure.Unknown
            }
        }
    }

    private fun getFailureForHttpException(httpException: HttpException): Failure {
        return when (httpException.code()) {
            in 400..499 -> Failure.Api(httpException.message())
            in 500..599 -> Failure.ServerError
            else -> Failure.Unknown
        }
    }
}