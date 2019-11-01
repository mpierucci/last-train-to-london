package com.mpierucci.lastratintolondon.usecase.failure

sealed class Failure {

    data class Api(val message: String) : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object Unknown : Failure()

    abstract class FeatureF(val message: String) : Failure()
}