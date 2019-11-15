package com.mpierucci.lasttraintolondon.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mpierucci.lasttraintolondon.R
import javax.inject.Inject

class LinesPreferencesFragment @Inject constructor(

) : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.lines_preferences, rootKey)
    }
}