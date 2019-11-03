package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mpierucci.lasttraintolondon.lines.domain.LineRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

// TODO test
class LinesStatusViewModel @Inject constructor(private val repository: LineRepository) :
    ViewModel() {

    private val lineStatusMapper = PresentationLineStatusMapper()
    val lineStatus = liveData(Dispatchers.IO) {
        val lineStatus = repository.getAll().map {
            lineStatusMapper.map(it)
        }
        emit(lineStatus)
    }
}