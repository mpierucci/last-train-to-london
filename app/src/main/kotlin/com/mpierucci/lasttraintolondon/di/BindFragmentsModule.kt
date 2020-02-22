package com.mpierucci.lasttraintolondon.di

import androidx.fragment.app.Fragment
import com.mpierucci.lasttraintolondon.core.fragment.FragmentClassKey
import com.mpierucci.lasttraintolondon.lines.presentation.LineStatusFragment
import com.mpierucci.lasttraintolondon.settings.ui.SettingsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
//TODO refactor this when adding dynamic features
abstract class BindFragmentsModule {

    @Binds
    @IntoMap
    @FragmentClassKey(LineStatusFragment::class)
    abstract fun bindLineStatusFragment(fragment: LineStatusFragment): Fragment

    @Binds
    @IntoMap
    @FragmentClassKey(SettingsFragment::class)
    abstract fun bindSettingsStatusFragment(screen: SettingsFragment): Fragment
}