package com.mpierucci.lasttraintolondon.tube.di

import com.mpierucci.lasttraintolondon.tube.presentation.TubeActivity
import dagger.Subcomponent


@Subcomponent(modules = [LineStatusesModule::class])
interface TubeActivityComponent {
    fun inject(activity: TubeActivity)
}