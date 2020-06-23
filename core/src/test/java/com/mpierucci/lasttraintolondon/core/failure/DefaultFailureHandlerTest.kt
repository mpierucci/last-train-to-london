package com.mpierucci.lasttraintolondon.core.failure

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class DefaultFailureHandlerTest {

    private val failureHandler = DefaultFailureHandler()

    @Test
    fun `get failure for unhandled error`() {
        val failure = failureHandler.getFailure(IndexOutOfBoundsException())

        assertThat(failure).isEqualTo(Failure.Unknown)
    }

    @Test
    fun `get failure for connection error`() {
        val failure = failureHandler.getFailure(IOException())

        assertThat(failure).isEqualTo(Failure.NetworkConnection)
    }


    @Test
    fun `get failure for server error`() {

        val serverResponse = Response.error<String>(505, mock())
        val serverException = HttpException(serverResponse)
        val failure = failureHandler.getFailure(serverException)

        assertThat(failure).isEqualTo(Failure.ServerError)
    }

    @Test
    fun `get failure for api error`() {
        val serverResponse = Response.error<String>(422, mock())
        val serverException = HttpException(serverResponse)
        val failure = failureHandler.getFailure(serverException)

        assertThat(failure).isInstanceOf(Failure.Api::class.java)
        assertThat((failure as Failure.Api).message).isEqualTo("Response.error()")
    }
}