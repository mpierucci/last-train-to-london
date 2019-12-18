package com.mpierucci.lasttraintolondon.core.dispatcher

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

interface DispatcherProvider {

    fun main(): CoroutineDispatcher = Dispatchers.Main

    fun default(): CoroutineDispatcher = Dispatchers.Default

    fun io(): CoroutineDispatcher = Dispatchers.IO

    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

class ReleaseDispatcher @Inject constructor() : DispatcherProvider

@Module
abstract class DispatcherModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(dispatcher: ReleaseDispatcher): DispatcherProvider
}