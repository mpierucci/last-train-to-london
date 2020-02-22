package com.mpierucci.lasttraintolondon.ristretto.mockwebserver

import com.mpierucci.lasttraintolondon.ristretto.FileReaderUtil.loadTestFile
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.QueueDispatcher
import okhttp3.mockwebserver.RecordedRequest

class FileResponseDispatcher(
    private val responseFileName: String
) : QueueDispatcher() {


    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        return try {
            val responseBody = loadTestFile(this.javaClass, responseFileName)

            MockResponse().setResponseCode(200).setBody(responseBody)
        } catch (exception: Exception) {
            errorResponse
        }
    }
}