package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.fragment.app.Fragment
import com.mpierucci.lasttraintolondon.core.fragment.FragmentClassKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindLineFragmentsModule {

    @Binds
    @IntoMap
    @FragmentClassKey(LineStatusFragment::class)
    abstract fun bindLineStatusFragment(fragment: LineStatusFragment): Fragment
}