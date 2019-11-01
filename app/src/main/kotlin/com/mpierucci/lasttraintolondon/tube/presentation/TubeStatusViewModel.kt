package com.mpierucci.lasttraintolondon.tube.presentation

import androidx.lifecycle.ViewModel
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository
import javax.inject.Inject

class TubeStatusViewModel @Inject constructor(private val repository: LineStatusRepository) :
    ViewModel() {

}