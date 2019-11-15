package com.mpierucci.lasttraintolondon.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mpierucci.lasttraintolondon.R

class LinesPreferencesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.lines_preferences, rootKey)
    }
}