package com.mpierucci.lasttraintolondon.lines.presentation

import android.content.Context
import com.mpierucci.lasttraintolondon.core.di.CoreComponent
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherModule
import com.mpierucci.lasttraintolondon.ristretto.di.TestNetworkModule
import com.mpierucci.lasttraintolondon.lines.di.LineStatusesModule
import com.mpierucci.lasttraintolondon.preferences.di.PreferencesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LineStatusesModule::class,
        BindLineFragmentsModule::class,
        TestNetworkModule::class,
        DispatcherModule::class,
        PreferencesModule::class]
)
@Singleton
interface LineTestComponent : CoreComponent {


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): LineTestComponent
    }

    fun inject(se: LineStatusScreenTest)
}