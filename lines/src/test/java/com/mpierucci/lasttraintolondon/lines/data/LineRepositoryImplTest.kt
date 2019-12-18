package com.mpierucci.lasttraintolondon.lines.data

import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.Mapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class LineRepositoryImplTest {

    private val api: LineStatusApi = mock()
    private val mapper: Mapper<RestLine, Line> = mock()
    private val repository = LineRepositoryImpl(api, mapper)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetAll() = runBlockingTest {
        val lineStatus = RestLine("", "", null, RestLineMode(""))

        whenever(api.getStatus()).thenReturn(listOf(lineStatus))

        repository.getAll()

        verify(api).getStatus()

        verify(mapper).map(any())
    }
}