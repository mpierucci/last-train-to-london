package com.mpierucci.lasttraintolondon.netwrok

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiKeyInterceptorTest {


    private val mockWebServer = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Test
    fun `Test correct keys are added on request`() {

        val testApi = retrofit.create(TestApi::class.java)

        val response = MockResponse()
        response.setResponseCode(200)
        response.setBody("{}")
        mockWebServer.enqueue(MockResponse())


        testApi.test().execute()

        val recordedRequest = mockWebServer.takeRequest()
        val recorderUrl = recordedRequest.requestUrl

        assertEquals(BuildConfig.TFL_APP_ID, recorderUrl?.queryParameter("app_id"))
        assertEquals(BuildConfig.TFL_APP_KEY, recorderUrl?.queryParameter("app_key"))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    private interface TestApi {

        @GET("/test")
        fun test(): Call<Unit>
    }
}