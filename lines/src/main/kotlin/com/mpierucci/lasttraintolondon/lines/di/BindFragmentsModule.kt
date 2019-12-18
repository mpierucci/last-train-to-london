package com.mpierucci.lasttraintolondon.lines.di

import androidx.fragment.app.Fragment
import com.mpierucci.lasttraintolondon.core.fragment.FragmentClassKey
import com.mpierucci.lasttraintolondon.lines.presentation.LineStatusScreen
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindFragmentsModule {

    @Binds
    @IntoMap
    @FragmentClassKey(LineStatusScreen::class)
    abstract fun bindLineStatusFragment(screen: LineStatusScreen): Fragment
}