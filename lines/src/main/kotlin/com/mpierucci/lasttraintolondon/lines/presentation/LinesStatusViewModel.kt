package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mpierucci.android.architecture.usecase.functional.map
import com.mpierucci.lasttraintolondon.core.dispatcher.DispatcherProvider
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.lines.domain.GetLinesStatusUseCase
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import javax.inject.Inject

// TODO test
class LinesStatusViewModel @Inject constructor(
    private val getLinesStatusUseCase: GetLinesStatusUseCase,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val lineStatusMapper = PresentationLineStatusMapper()

    val lineStatuses = liveData {
        emit(ViewContract.Loading(true))
        with(getLinesStatusUseCase.execute(Unit)) {
            yield()
            withContext(dispatcherProvider.default()) {
                map { lines -> lines.map { lineStatusMapper.map(it) } }
                    .fold({ emit(ViewContract.Error(it)) }, { emit(ViewContract.Success(it)) })
            }
        }
    }
}