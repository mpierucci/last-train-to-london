package com.mpierucci.lasttraintolondon.mvvm

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Custom Live data to propagate one time actions
 * Created by mpierucci on 1/3/18.
 */
class ActionLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        check(!hasObservers()) {
            "Only one observer at a time may subscribe to a ActionLiveData"
        }

        super.observe(owner, Observer { data ->
            if (data == null) return@Observer
            observer.onChanged(data)
            value = null
        })
    }

    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}
