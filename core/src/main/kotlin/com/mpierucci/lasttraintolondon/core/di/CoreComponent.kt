package com.mpierucci.lasttraintolondon.core.di

import android.content.Context
import android.content.SharedPreferences
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherModule
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.core.failure.FailureHandler
import com.mpierucci.lasttraintolondon.core.failure.FailureModule
import com.mpierucci.lasttraintolondon.netwrok.NetworkModule
import com.mpierucci.lasttraintolondon.preferences.di.DefaultPreferences
import com.mpierucci.lasttraintolondon.preferences.di.PreferencesModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class, DispatcherModule::class, PreferencesModule::class,
        FailureModule::class]
)
@Singleton
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val retrofit: Retrofit

    val context: Context

    @get:DefaultPreferences
    val defaultPreferences: SharedPreferences

    val dispatcherProvider: DispatcherProvider

    val failure: FailureHandler
}