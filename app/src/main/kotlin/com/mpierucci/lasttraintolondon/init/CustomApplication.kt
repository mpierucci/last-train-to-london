package com.mpierucci.lasttraintolondon.init

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mpierucci.lasttraintolondon.core.di.DaggerCoreComponent
import com.mpierucci.lasttraintolondon.di.AppComponent
import com.mpierucci.lasttraintolondon.di.DaggerAppComponent
import com.mpierucci.lasttraintolondon.settings.domain.SettingsRepository
import javax.inject.Inject

class CustomApplication : Application(), AppComponent.ComponentProvider {

    @Inject
    lateinit var appInitializer: AppInitializers

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override val component by lazy {
        DaggerAppComponent.factory().create(
            DaggerCoreComponent.factory().create(this)
        )
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appInitializer.init(this)

        AppCompatDelegate.setDefaultNightMode(settingsRepository.getAppThemeMode())
    }
}