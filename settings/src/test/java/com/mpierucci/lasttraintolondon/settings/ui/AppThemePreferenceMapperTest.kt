package com.mpierucci.lasttraintolondon.settings.ui

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.mpierucci.lasttraintolondon.settings.R
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test

class AppThemePreferenceMapperTest {

    /*
    Not fond of Roboelectric. This and future UI test is more than enough
     */
    private val context = mock<Context> {
        on { getString(R.string.entry_light_theme) } doReturn "Light"
        on { getString(R.string.entry_dark_theme) } doReturn "Dark"
        on { getString(R.string.entry_system_default_theme) } doReturn "System default"
        on { getString(R.string.entry_battery_saver_theme) } doReturn "Follow battery saver"
    }

    private val preferenceMapper = AppThemePreferenceMapper(context)


    @Test
    fun `Test light preference`() {
        assertEquals(
            AppCompatDelegate.MODE_NIGHT_NO,
            preferenceMapper.mapPreference(context.getString(R.string.entry_light_theme))
        )
    }

    @Test
    fun `Test dark preference`() {
        assertEquals(
            AppCompatDelegate.MODE_NIGHT_YES,
            preferenceMapper.mapPreference(context.getString(R.string.entry_dark_theme))
        )
    }

    @Test
    fun `Test syste default preference`() {
        assertEquals(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
            preferenceMapper.mapPreference(context.getString(R.string.entry_system_default_theme))
        )
    }

    @Test
    fun `Test battery saver preference`() {
        assertEquals(
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY,
            preferenceMapper.mapPreference(context.getString(R.string.entry_battery_saver_theme))
        )
    }

    @Test
    fun `Test invalid preference`() {
        assertEquals(
            AppCompatDelegate.MODE_NIGHT_NO,
            preferenceMapper.mapPreference("this preference is invalid")
        )
    }
}