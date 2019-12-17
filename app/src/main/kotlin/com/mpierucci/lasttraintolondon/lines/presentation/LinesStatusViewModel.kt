package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpierucci.android.architecture.usecase.failure.Failure
import com.mpierucci.android.architecture.usecase.functional.map
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.lines.domain.GetLinesStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
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
            with(getLinesStatusUseCase.execute(Unit)) {
                yield()
                withContext(Dispatchers.Default) {
                    map { lines ->
                        lines.map { lineStatusMapper.map(it) }
                    }
                }
            }.fold(::handleError, ::handleSuccess)

            _lineStatus.value = ViewContract.Loading(false)
        }
    }

    private fun handleSuccess(status: List<PresentationLineStatus>) {
        _lineStatus.value = ViewContract.Success(status)
    }

    private fun handleError(failure: Failure) {
        _lineStatus.value = ViewContract.Error(failure)
    }
}