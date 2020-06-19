package com.mpierucci.lasttraintolondon.settings.ui

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.mpierucci.lasttraintolondon.settings.R
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class AppThemePreferenceMapper @Inject constructor(@ApplicationContext context: Context) {
    private val light = context.getString(R.string.entry_light_theme)
    private val dark = context.getString(R.string.entry_dark_theme)
    private val systemDefault = context.getString(R.string.entry_system_default_theme)
    private val batterySaver = context.getString(R.string.entry_battery_saver_theme)

    fun mapPreference(preference: String): Int {
        return when (preference) {
            light -> AppCompatDelegate.MODE_NIGHT_NO
            dark -> AppCompatDelegate.MODE_NIGHT_YES
            systemDefault -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            batterySaver -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            else -> {
                Timber.e("Invalid App theme preference selected: $preference")
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }
}