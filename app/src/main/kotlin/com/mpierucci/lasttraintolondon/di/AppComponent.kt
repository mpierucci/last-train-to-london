package com.mpierucci.lasttraintolondon.di

import android.content.Context
import com.mpierucci.lasttraintolondon.netwrok.NetworkModule
import com.mpierucci.lasttraintolondon.tube.di.TubeActivityComponent
import com.mpierucci.lasttraintolondon.tube.di.LineStatusesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun plus(module: LineStatusesModule): TubeActivityComponent

    interface ComponentProvider {
        val component: AppComponent
    }
}