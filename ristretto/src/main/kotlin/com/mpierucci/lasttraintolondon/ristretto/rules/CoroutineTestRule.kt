package com.mpierucci.lasttraintolondon.ristretto.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


/**
 * Swaps the Main dispatcher with [TestCoroutineDispatcher]
 */
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description) = object : Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
        }
    }
}