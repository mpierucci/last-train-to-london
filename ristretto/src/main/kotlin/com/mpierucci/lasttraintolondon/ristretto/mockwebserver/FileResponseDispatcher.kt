package com.mpierucci.lasttraintolondon.ristretto.mockwebserver

import android.net.Uri
import com.mpierucci.lasttraintolondon.ristretto.FileReaderUtil.loadTestFile
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.QueueDispatcher
import okhttp3.mockwebserver.RecordedRequest

/**
 * Custom [Dispatcher] to return json files based on the path of the request it receives.
 *
 * @param pathToFilesMap map where keys represent path that te dispatcher may receive, and values
 * are full path to a json file what wil be returned as a response.ø
 */
class FileResponseDispatcher(
    private val pathToFilesMap: Map<String, String>
) : QueueDispatcher() {


    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse

        val responseFile = pathToFilesMap[pathWithoutQueryParams] ?: return errorResponse

        return try {
            val responseBody = loadTestFile(this.javaClass, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)

        } catch (exception: Exception) {
            errorResponse
        }
    }
}