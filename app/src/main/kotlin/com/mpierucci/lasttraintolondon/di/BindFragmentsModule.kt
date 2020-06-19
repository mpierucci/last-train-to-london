package com.mpierucci.lasttraintolondon.di

import androidx.fragment.app.Fragment
import com.mpierucci.lasttraintolondon.core.fragment.FragmentClassKey
import com.mpierucci.lasttraintolondon.lines.presentation.LineStatusScreen
import com.mpierucci.lasttraintolondon.settings.ui.SettingsFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
//TODO refactor this when adding dynamic features
@InstallIn(ActivityComponent::class)
abstract class BindFragmentsModule {

    @Binds
    @IntoMap
    @FragmentClassKey(LineStatusScreen::class)
    abstract fun bindLineStatusFragment(screen: LineStatusScreen): Fragment

    @Binds
    @IntoMap
    @FragmentClassKey(SettingsFragment::class)
    abstract fun bindSettingsStatusFragment(screen: SettingsFragment): Fragment
}