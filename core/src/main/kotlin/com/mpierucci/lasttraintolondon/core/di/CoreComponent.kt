package com.mpierucci.lasttraintolondon.core.di

import com.mpierucci.lasttraintolondon.netwrok.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }

    fun provideRetrofit(): Retrofit
}