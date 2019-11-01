package com.mpierucci.lasttraintolondon.tube.data

import retrofit2.http.GET

interface LineStatusApi {

    @GET("line/mode/tube/status")
    suspend fun getStatus(): List<RestLineStatus>
}