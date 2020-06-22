package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.core.failure.Failure
import com.mpierucci.lasttraintolondon.core.failure.FailureHandler
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull

import javax.inject.Inject


// TODO test
@ExperimentalCoroutinesApi
class LinesStatusViewModel @Inject constructor(
    private val linesRepository: LineRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val failureHandler: FailureHandler
) : ViewModel() {

    private val viewState = MutableStateFlow<LinesViewState?>(null)

    private val viewActions = viewModelScope.actionsActor()

    // this way we dont need the extra ViewContract
    internal val lineStatuses = viewState.filterNotNull().catch {
        emit(LinesViewState.Error(Failure.Unknown))
    }

    private fun fetchLinesStatues() {
        viewModelScope.launch {
            viewState.value = LinesViewState.Loading

            runCatching {
                val lines = linesRepository.getAll()
                yield()
                withContext(dispatcherProvider.default()) {
                    viewState.value = LinesViewState.Success(
                        lines.map { it.toPresentationModel() }
                    )
                }
            }.onFailure {
                viewState.value = LinesViewState.Error(failureHandler.getFailure(it))
            }
        }
    }

    internal fun postAction(action: LinesViewAction) = viewModelScope.launch {
        viewActions.send(action)
    }

    private fun CoroutineScope.actionsActor() = actor<LinesViewAction> {
        for (message in channel) {
            when (message) {
                LinesViewAction.FetchStatus -> {
                    fetchLinesStatues()
                }
            }
        }
    }
}