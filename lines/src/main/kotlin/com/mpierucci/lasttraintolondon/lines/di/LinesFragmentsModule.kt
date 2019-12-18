package com.mpierucci.lasttraintolondon.lines.di

import androidx.fragment.app.Fragment
import com.mpierucci.lasttraintolondon.core.fragment.FragmentClassKey
import com.mpierucci.lasttraintolondon.lines.presentation.LineStatusFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LinesFragmentsModule {

    @Binds
    @IntoMap
    @FragmentClassKey(LineStatusFragment::class)
    abstract fun bindLineStatusFragment(fragment: LineStatusFragment): Fragment
}