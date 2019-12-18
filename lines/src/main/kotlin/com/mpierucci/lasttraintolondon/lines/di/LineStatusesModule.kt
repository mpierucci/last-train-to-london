package com.mpierucci.lasttraintolondon.lines.di

import com.mpierucci.lasttraintolondon.lines.data.LineRepositoryImpl
import com.mpierucci.lasttraintolondon.lines.data.LineStatusApi
import com.mpierucci.lasttraintolondon.lines.data.LineStatusMapper
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
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
    ): LineRepository = LineRepositoryImpl(api, LineStatusMapper())
}