package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.data.LineStatusApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object LineStatusesModule {

    @Provides
    @Reusable
    fun provideLineStatusApi(retrofit: Retrofit): LineStatusApi =
        retrofit.create(LineStatusApi::class.java)
}