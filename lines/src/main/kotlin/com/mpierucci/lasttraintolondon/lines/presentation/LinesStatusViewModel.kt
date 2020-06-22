package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject


// TODO test
@ExperimentalCoroutinesApi
class LinesStatusViewModel @Inject constructor(
    private val linesRepository: LineRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val viewState = MutableStateFlow<LinesViewState?>(null)

    private val viewActions = viewModelScope.actionsActor()

    // this way we dont need the extra ViewContract
    internal val lineStatuses = viewState.filterNotNull()

    private fun fetchLinesStatues() {
        viewModelScope.launch {
            viewState.value = LinesViewState.Loading
            with(linesRepository.getAll()) {
                yield() //TODO right usage?
                withContext(dispatcherProvider.default()) {
                    map { lines -> lines.map { it.toPresentationModel() } }
                        .fold(
                            { error ->
                                //TODO once approach is defined, re introduce Failure and map here
                                viewState.value = LinesViewState.Error(error)
                            },
                            { lines ->
                                viewState.value = LinesViewState.Success(lines)
                            }
                        )
                }
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