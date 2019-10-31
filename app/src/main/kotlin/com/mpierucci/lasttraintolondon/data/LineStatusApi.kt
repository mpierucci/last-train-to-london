package com.mpierucci.lasttraintolondon.data

import retrofit2.http.GET

interface LineStatusApi {

    @GET("line/mode/tube/status")
    fun getStatus(): List<RestLineStatus>
}