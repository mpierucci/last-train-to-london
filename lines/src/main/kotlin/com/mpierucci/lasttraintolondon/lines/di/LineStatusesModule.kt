package com.mpierucci.lasttraintolondon.lines.di

import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.lines.data.LineRepositoryImpl
import com.mpierucci.lasttraintolondon.lines.data.LineStatusApi
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object LineStatusesModule {

    @Provides
    @Reusable
    fun provideLineStatusApi(retrofit: Retrofit): LineStatusApi =
        retrofit.create(LineStatusApi::class.java)

    @Provides
    @Reusable
    fun provideLineStatusRepository(
        api: LineStatusApi,
        dispatcherProvider: DispatcherProvider
    ): LineRepository = LineRepositoryImpl(api, dispatcherProvider)
}