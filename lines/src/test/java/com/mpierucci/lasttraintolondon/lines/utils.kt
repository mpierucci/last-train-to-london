package com.mpierucci.lasttraintolondon.lines

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.InputStreamReader

//TODO https://github.com/mpierucci/last-train-to-london/issues/33


inline fun <reified T> loadModelFromTestFile(caller: Class<*>, fileName: String): T {
    val inputStream = caller.classLoader?.getResourceAsStream(fileName)
    val inputStreamReader = InputStreamReader(inputStream)

    return try {
        GsonBuilder().create().fromJson<T>(inputStreamReader, object : TypeToken<T>() {}.type)
    } finally {
        inputStream?.close()
        inputStreamReader.close()
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    val testDispatcherProvider = object : DispatcherProvider {
        override fun default(): CoroutineDispatcher = testCoroutineDispatcher
        override fun io(): CoroutineDispatcher = testCoroutineDispatcher
        override fun main(): CoroutineDispatcher = testCoroutineDispatcher
        override fun unconfined(): CoroutineDispatcher = testCoroutineDispatcher
    }

    override fun starting(  description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
    
}