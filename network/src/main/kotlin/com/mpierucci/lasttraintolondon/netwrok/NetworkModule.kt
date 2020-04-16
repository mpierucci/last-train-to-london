package com.mpierucci.lasttraintolondon.netwrok

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mpierucci.lasttraintolondon.network.interceptor.InspectorInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val BASE_URL = "https://api.tfl.gov.uk/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addNetworkInterceptor(InspectorInterceptor())
            .build()
    }

    @OptIn(UnstableDefault::class)
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                Json(JsonConfiguration(ignoreUnknownKeys = true))
                    .asConverterFactory(contentType)
            )
            .build()
    }
}
