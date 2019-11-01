package com.mpierucci.lasttraintolondon.tube.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TubeStatusViewModel @Inject constructor(private val repository: LineStatusRepository) :
    ViewModel() {

    val lineStatus = liveData(Dispatchers.IO) {
        emit(repository.getAll())
    }
}