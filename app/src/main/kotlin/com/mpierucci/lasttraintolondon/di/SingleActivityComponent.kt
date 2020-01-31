package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.ui.SingleActivity
import dagger.Subcomponent

@Subcomponent
interface SingleActivityComponent {
    fun inject(activity: SingleActivity)
}