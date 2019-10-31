package com.mpierucci.lasttraintolondon.di

import android.content.Context
import com.mpierucci.lasttraintolondon.netwrok.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}