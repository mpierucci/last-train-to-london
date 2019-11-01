package com.mpierucci.lasttraintolondon.tube.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

// TODO test
class LinesStatusViewModel @Inject constructor(private val repository: LineStatusRepository) :
    ViewModel() {
    init {
        Log.e("ViewModel","Init")
    }

    private val lineStatusMapper = PresentationLineStatusMapper()
    val lineStatus = liveData(Dispatchers.IO) {
        val lineStatus = repository.getAll().map {
            lineStatusMapper.map(it)
        }
        emit(lineStatus)
    }
}