package com.mpierucci.lasttraintolondon.tube.di

import com.mpierucci.lasttraintolondon.tube.data.LineStatusApi
import com.mpierucci.lasttraintolondon.tube.data.LineStatusMapper
import com.mpierucci.lasttraintolondon.tube.data.LineStatusRepositoryImpl
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
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
    fun provideLineStatusRepository(
        api: LineStatusApi
    ): LineStatusRepository = LineStatusRepositoryImpl(api, LineStatusMapper())
}