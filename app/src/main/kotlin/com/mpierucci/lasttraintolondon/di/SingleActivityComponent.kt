package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.lines.di.LineStatusesModule
import com.mpierucci.lasttraintolondon.lines.di.BindFragmentsModule
import com.mpierucci.lasttraintolondon.ui.SingleActivity
import dagger.Subcomponent

@Subcomponent(modules = [LineStatusesModule::class, BindFragmentsModule::class])
interface SingleActivityComponent {
    fun inject(activity: SingleActivity)
}