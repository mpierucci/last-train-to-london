package com.mpierucci.lastratintolondon.usecase.rx

sealed class Failure {

    data class Api(val message: String) : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object Unknown : Failure()

    abstract class FeatureF(val message: String) : Failure()
}