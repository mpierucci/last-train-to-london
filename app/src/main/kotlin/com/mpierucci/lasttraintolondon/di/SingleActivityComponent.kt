package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.lines.di.LineStatusesModule
import com.mpierucci.lasttraintolondon.lines.di.LinesFragmentsModule
import com.mpierucci.lasttraintolondon.lines.presentation.LinesActivity
import dagger.Subcomponent

@Subcomponent(modules = [LineStatusesModule::class, LinesFragmentsModule::class])
interface SingleActivityComponent {
    fun inject(activity: LinesActivity)
}