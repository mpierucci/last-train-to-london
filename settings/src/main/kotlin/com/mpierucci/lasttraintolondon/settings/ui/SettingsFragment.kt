package com.mpierucci.lasttraintolondon.settings.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.mpierucci.lasttraintolondon.settings.R
import javax.inject.Inject

class SettingsFragment @Inject constructor(private val preferenceMapper: AppThemePreferenceMapper) :
    PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findPreference<ListPreference>(
            getString(R.string.key_theme_preference)
        )?.setOnPreferenceChangeListener { _, themePreference ->

            AppCompatDelegate.setDefaultNightMode(
                preferenceMapper.mapPreference((themePreference as String))
            )
            true
        }
    }
}