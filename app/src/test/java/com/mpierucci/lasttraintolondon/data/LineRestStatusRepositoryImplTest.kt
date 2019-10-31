package com.mpierucci.lasttraintolondon.data

import com.mpierucci.lasttraintolondon.domain.LineStatus
import com.mpierucci.lasttraintolondon.domain.Mapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class LineRestStatusRepositoryImplTest {

    private val api: LineStatusApi = mock()
    private val mapper: Mapper<RestLineStatus, LineStatus> = mock()
    private val repository = LineStatusRepositoryImpl(api, mapper)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetAll() = runBlockingTest {

        val lineStatus = RestLineStatus("", "", null)

        whenever(api.getStatus()).thenReturn(listOf(lineStatus))

        repository.getAll()

        verify(api).getStatus()

        verify(mapper).map(any())
    }
}