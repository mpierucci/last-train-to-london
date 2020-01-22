package com.mpierucci.lasttraintolondon.preferences.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object PreferencesModule {

    @Reusable
    @Provides
    @DefaultPreferences
    fun provideDefaultPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}