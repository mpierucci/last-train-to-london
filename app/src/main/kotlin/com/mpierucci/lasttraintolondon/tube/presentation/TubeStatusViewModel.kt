package com.mpierucci.lasttraintolondon.tube.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

// TODO test
class TubeStatusViewModel @Inject constructor(private val repository: LineStatusRepository) :
    ViewModel() {

    private val lineStatusMapper = PresentationLineStatusMapper()
    val lineStatus = liveData(Dispatchers.IO) {
        val lineStatus = repository.getAll().map {
            lineStatusMapper.map(it)
        }
        emit(lineStatus)
    }
}