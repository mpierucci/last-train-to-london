package com.mpierucci.lasttraintolondon.lines.data

import retrofit2.http.GET

interface LineStatusApi {

    @GET("line/mode/tube,dlr,overground/status")
    suspend fun getStatus(): List<RestLine>
}