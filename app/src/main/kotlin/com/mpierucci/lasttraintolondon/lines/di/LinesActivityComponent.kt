package com.mpierucci.lasttraintolondon.lines.di

import com.mpierucci.lasttraintolondon.lines.presentation.LinesActivity
import dagger.Subcomponent

@Subcomponent(modules = [LineStatusesModule::class, LinesFragmentsModule::class])
interface LinesActivityComponent {
    fun inject(activity: LinesActivity)
}