package com.mpierucci.lasttraintolondon.lines.presentation.di

import android.content.Context
import com.mpierucci.lasttraintolondon.core.di.CoreComponent
import com.mpierucci.lasttraintolondon.core.failure.FailureModule
import com.mpierucci.lasttraintolondon.lines.di.LineStatusesModule
import com.mpierucci.lasttraintolondon.lines.presentation.LineStatusScreenTest
import com.mpierucci.lasttraintolondon.preferences.di.PreferencesModule
import com.mpierucci.lasttraintolondon.ristretto.di.TestNetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LineStatusesModule::class,
        BindLineFragmentsModule::class,
        TestNetworkModule::class,
        PreferencesModule::class,
        FailureModule::class]
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