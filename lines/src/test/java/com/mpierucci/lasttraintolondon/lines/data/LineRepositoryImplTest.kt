package com.mpierucci.lasttraintolondon.lines.data


import com.google.common.truth.Truth.assertThat
import com.mpierucci.lasttraintolondon.lines.domain.Line
import com.mpierucci.lasttraintolondon.lines.domain.LineId
import com.mpierucci.lasttraintolondon.lines.domain.LineMode
import com.mpierucci.lasttraintolondon.ristretto.rules.CoroutineTestRule
import com.mpierucci.lasttraintolondon.ristretto.rules.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class LineRepositoryImplTest {


    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()


    @ExperimentalCoroutinesApi
    @Test
    fun testGetAll() = runBlockingTest {
        val api = FakeApi()
        val repository = LineRepositoryImpl(api, TestDispatcherProvider())

        val expected = listOf(Line(LineId(""), "", LineMode.Undefined, emptyList()))

        val actual = repository.getAll()

        assertThat(actual).isEqualTo(expected)
    }


    private class FakeApi : LineStatusApi {
        val lineStatus = RestLine("", "", null, "")

        override suspend fun getStatus() = listOf(lineStatus)
    }
}