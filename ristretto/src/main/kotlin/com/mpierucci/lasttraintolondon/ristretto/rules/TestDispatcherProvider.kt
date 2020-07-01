package com.mpierucci.lasttraintolondon.ristretto.rules

import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Inject

class TestDispatcherProvider @Inject constructor() : DispatcherProvider {

    private val testDispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher = testDispatcher
    override fun io(): CoroutineDispatcher = testDispatcher
    override fun main(): CoroutineDispatcher = testDispatcher
    override fun unconfined(): CoroutineDispatcher = testDispatcher
}