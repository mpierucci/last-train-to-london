package com.mpierucci.lasttraintolondon.init

import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import com.mpierucci.lasttraintolondon.network.interceptor.InspectorInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppInitializerModule {

    @Binds
    @IntoSet
    abstract fun bindInterceptorInitializer(initializer: InspectorInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun bindTimberInitializer(initializer: TimberInitializer): AppInitializer
}