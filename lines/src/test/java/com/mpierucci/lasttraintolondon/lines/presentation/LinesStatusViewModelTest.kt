package com.mpierucci.lasttraintolondon.lines.presentation


import com.google.common.truth.Truth.assertThat
import com.mpierucci.lasttraintolondon.core.failure.DefaultFailureHandler
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import com.mpierucci.lasttraintolondon.ristretto.rules.CoroutineTestRule
import com.mpierucci.lasttraintolondon.ristretto.rules.TestDispatcherProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class LinesStatusViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val fakeRepository = mock<LineRepository>()

    //thanks to https://proandroiddev.com/from-rxjava-to-kotlin-flow-testing-42f1641d8433
    @Test
    fun `post fetch action with successful fetching`() = runBlockingTest {
        val viewModel = getViewModel()

        whenever(fakeRepository.getAll()).thenReturn(emptyList())

        val viewStates = mutableListOf<LinesViewState>()

        val job = launch {
            viewModel.lineStatuses
                .collect { viewStates.add(it) }
        }

        viewModel.postAction(LinesViewAction.FetchStatus)

        assertThat(viewStates[0]).isInstanceOf(LinesViewState.Loading::class.java)
        assertThat(viewStates[1]).isInstanceOf(LinesViewState.Success::class.java)

        job.cancel()
    }

    @Test
    fun `post fetch action with unsuccessful fetching`() = runBlockingTest {
        val viewModel = getViewModel()

        whenever(fakeRepository.getAll()).thenThrow(IllegalStateException())

        val viewStates = mutableListOf<LinesViewState>()

        val job = launch {
            viewModel.lineStatuses
                .collect { viewStates.add(it) }
        }

        viewModel.postAction(LinesViewAction.FetchStatus)

        assertThat(viewStates[0]).isInstanceOf(LinesViewState.Loading::class.java)
        assertThat(viewStates[1]).isInstanceOf(LinesViewState.Error::class.java)

        job.cancel()
    }

    @Test
    fun `filters initial null view state value`() = runBlockingTest {
        val viewModel = getViewModel()

        whenever(fakeRepository.getAll()).thenReturn(emptyList())

        val viewStates = mutableListOf<LinesViewState>()

        val job = launch {
            viewModel.lineStatuses
                .collect { viewStates.add(it) }
        }

        assertThat(viewStates).isEmpty()

        job.cancel()
    }


    // View model needs to be instantiated after the coroutine rule replaced the main Dispatcher
    private fun getViewModel(): LinesStatusViewModel {
        return LinesStatusViewModel(
            fakeRepository,
            TestDispatcherProvider(),
            DefaultFailureHandler()
        )
    }
}