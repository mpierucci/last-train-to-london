package com.mpierucci.lasttraintolondon.init

import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import com.mpierucci.lasttraintolondon.core.init.InspectorInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppInitializerModule {

    @Binds
    @IntoSet
    abstract fun bindInterceptorInitializer(initializer: InspectorInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun bindTimberInitializer(initializer: TimberInitializer): AppInitializer
}