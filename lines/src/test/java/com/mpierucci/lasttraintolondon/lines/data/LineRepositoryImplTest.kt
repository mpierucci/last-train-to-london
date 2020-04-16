package com.mpierucci.lasttraintolondon.lines.data


import com.mpierucci.lasttraintolondon.ristretto.rules.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class LineRepositoryImplTest {


    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val api: LineStatusApi = mock()
    private val repository =
        LineRepositoryImpl(api, coroutinesTestRule.testDispatcherProvider)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetAll() = coroutinesTestRule.testCoroutineDispatcher.runBlockingTest {
        val lineStatus = RestLine("", "", null, "")

        whenever(api.getStatus()).thenReturn(listOf(lineStatus))

        repository.getAll()

        verify(api).getStatus()
    }
}