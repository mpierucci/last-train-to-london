package com.mpierucci.lasttraintolondon.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxViewModel(private val compositeDisposable: CompositeDisposable) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
