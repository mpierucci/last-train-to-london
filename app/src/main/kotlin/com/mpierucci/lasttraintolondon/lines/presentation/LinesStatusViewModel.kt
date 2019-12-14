package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpierucci.lastratintolondon.usecase.Either
import com.mpierucci.lastratintolondon.usecase.failure.Failure
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.lines.domain.GetLinesStatusUseCase
import com.mpierucci.lasttraintolondon.lines.domain.Line
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


// TODO test
class LinesStatusViewModel @Inject constructor(
    private val getLinesStatusUseCase: GetLinesStatusUseCase
) :
    ViewModel() {

    private val lineStatusMapper = PresentationLineStatusMapper()
    private val _lineStatus = MutableLiveData<ViewContract<List<PresentationLineStatus>>>()
    val lineStatus: LiveData<ViewContract<List<PresentationLineStatus>>> = _lineStatus

    init {
        viewModelScope.launch {
            _lineStatus.value = ViewContract.Loading(true)
            getLinesStatusUseCase.execute().suspendedEither(::handleError, ::handleSuccess)
            _lineStatus.value = ViewContract.Loading(false)
        }
    }


    private suspend fun handleSuccess(status: List<Line>) {
        val result = withContext(Dispatchers.Default) {
            status.map { lineStatusMapper.map(it) }
        }
        _lineStatus.value = ViewContract.Success(result)
    }

    private fun handleError(failure: Failure) {
        _lineStatus.value = ViewContract.Error(failure)
    }


    /*
    TODO
    This is really a limitation based on the need to map the presentation model
    Don´t really want to pollute Either´s API
    Research some improvement
     */
    private suspend fun <L, R> Either<L, R>.suspendedEither(
        lefFunc: (L) -> Any,
        rightFunc: suspend (R) -> Any
    ): Any {
        return when (this) {
            is Either.Left -> lefFunc(value)
            is Either.Right -> rightFunc(value)
        }
    }
}