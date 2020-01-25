package com.mpierucci.lasttraintolondon.settings.data

import android.content.Context
import android.content.SharedPreferences
import com.mpierucci.lasttraintolondon.preferences.di.DefaultPreferences
import com.mpierucci.lasttraintolondon.settings.R
import com.mpierucci.lasttraintolondon.settings.domain.SettingsRepository
import com.mpierucci.lasttraintolondon.settings.ui.AppThemePreferenceMapper
import javax.inject.Inject


class SettingsRepositoryImpl @Inject constructor(
    @DefaultPreferences private val sharedPreferences: SharedPreferences,
    private val themePreferenceMapper: AppThemePreferenceMapper,
    private val context: Context
) : SettingsRepository {
    override fun getAppThemeMode(): Int {
        val preference = sharedPreferences.getString(
            context.getString(R.string.key_theme_preference),
            context.getString(R.string.entry_light_theme)
        ).orEmpty()
        return themePreferenceMapper.mapPreference(preference)
    }
}