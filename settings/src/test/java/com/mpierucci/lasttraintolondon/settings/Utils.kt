package com.mpierucci.lasttraintolondon.settings

import android.content.Context
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock

/*
Not fond of Roboelectric. This and future UI test is more than enough
 */
internal val preferencesMockedContext = mock<Context> {
    on { getString(R.string.entry_light_theme) } doReturn "Light"
    on { getString(R.string.entry_dark_theme) } doReturn "Dark"
    on { getString(R.string.entry_system_default_theme) } doReturn "System default"
    on { getString(R.string.entry_battery_saver_theme) } doReturn "Follow battery saver"
    on { getString(R.string.app_theme_default_value) } doReturn "Follow battery saver"
}
