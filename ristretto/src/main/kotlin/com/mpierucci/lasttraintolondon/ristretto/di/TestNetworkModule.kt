package com.mpierucci.lasttraintolondon.ristretto.di

import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.ristretto.rules.TestDispatcherProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object TestNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDispatcher(testDispatcherProvider: TestDispatcherProvider): DispatcherProvider =
        testDispatcherProvider
}
