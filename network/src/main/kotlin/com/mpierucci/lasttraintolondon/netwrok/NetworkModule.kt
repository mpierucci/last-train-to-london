package com.mpierucci.lasttraintolondon.netwrok

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mpierucci.lasttraintolondon.network.interceptor.InspectorInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
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

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory(contentType)
            )
            .build()
    }
}
