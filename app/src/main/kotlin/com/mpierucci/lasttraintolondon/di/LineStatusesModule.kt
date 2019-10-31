package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.data.LineStatusApi
import com.mpierucci.lasttraintolondon.data.LineStatusMapper
import com.mpierucci.lasttraintolondon.data.RestLineStatus
import com.mpierucci.lasttraintolondon.domain.LineStatus
import com.mpierucci.lasttraintolondon.domain.Mapper
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

    @Provides
    @Reusable
    fun provideLineStatusMapper(): Mapper<RestLineStatus, LineStatus> = LineStatusMapper()
}