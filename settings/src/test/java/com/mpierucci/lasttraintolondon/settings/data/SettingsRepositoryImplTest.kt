package com.mpierucci.lasttraintolondon.settings.data

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.google.common.truth.Truth.assertThat
import com.mpierucci.lasttraintolondon.settings.R
import com.mpierucci.lasttraintolondon.settings.preferencesMockedContext
import com.mpierucci.lasttraintolondon.settings.ui.AppThemePreferenceMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class SettingsRepositoryImplTest {

    private val sharedPreferences = mock<SharedPreferences>()
    private val context = preferencesMockedContext.apply {
        whenever(this.getString(R.string.key_theme_preference)).thenReturn("app_theme")
    }
    private val repository = SettingsRepositoryImpl(
        sharedPreferences = sharedPreferences,
        themePreferenceMapper = AppThemePreferenceMapper(context),
        context = context
    )

    private fun mockPreferences(mockedThemePreference: String) {
        whenever(
            sharedPreferences.getString(eq(context.getString(R.string.key_theme_preference)), any())
        ).thenReturn(mockedThemePreference)
    }


    @Test
    fun `Test light theme preference`() {
        mockPreferences(context.getString(R.string.entry_light_theme))

        assertThat(repository.getAppThemeMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @Test
    fun `Test dark theme preference`() {
        mockPreferences(context.getString(R.string.entry_dark_theme))

        assertThat(repository.getAppThemeMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `Test system default  theme preference`() {
        mockPreferences(context.getString(R.string.entry_system_default_theme))

        assertThat(repository.getAppThemeMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    @Test
    fun `Test  battery saver theme preference`() {
        mockPreferences(context.getString(R.string.entry_battery_saver_theme))


        assertThat(repository.getAppThemeMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
    }

    @Test
    fun `Test  invalid  preference`() {
        mockPreferences("invalid")

        assertThat(repository.getAppThemeMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }
}