package com.mpierucci.lasttraintolondon.core.failure

import dagger.Binds
import dagger.Module

@Module
abstract class FailureModule {
    @Binds
    abstract fun bindFailureHandler(handler: DefaultFailureHandler): FailureHandler
}