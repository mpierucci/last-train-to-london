package com.mpierucci.lasttraintolondon.init

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mpierucci.lasttraintolondon.settings.domain.SettingsRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CustomApplication : Application() {

    @Inject
    lateinit var appInitializer: AppInitializers

    @Inject
    lateinit var settingsRepository: SettingsRepository


    override fun onCreate() {
        super.onCreate()
        appInitializer.init(this)

        AppCompatDelegate.setDefaultNightMode(settingsRepository.getAppThemeMode())
    }
}