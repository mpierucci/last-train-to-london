package com.mpierucci.lasttraintolondon.di

import com.mpierucci.lasttraintolondon.core.di.CoreComponent
import com.mpierucci.lasttraintolondon.init.AppInitializerModule
import com.mpierucci.lasttraintolondon.init.CustomApplication
import dagger.Component

@Component(
    modules = [AppInitializerModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): AppComponent
    }

    fun inject(application: CustomApplication)

    fun plus(): SingleActivityComponent

    interface ComponentProvider {
        val component: AppComponent
    }
}