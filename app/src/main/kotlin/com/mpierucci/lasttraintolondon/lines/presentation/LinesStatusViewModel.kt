package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpierucci.lastratintolondon.usecase.failure.Failure
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.lines.domain.GetLinesStatusUseCase
import com.mpierucci.lasttraintolondon.lines.domain.Line
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            getLinesStatusUseCase.execute().either(::handleError, ::handleSuccess)
        }
    }

    private fun handleSuccess(status: List<Line>) {
        _lineStatus.postValue(
            ViewContract.Success(status.map { lineStatusMapper.map(it) })
        )
    }

    private fun handleError(failure: Failure) {
        _lineStatus.postValue(
            ViewContract.Error(failure)
        )
    }
}