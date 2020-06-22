package com.mpierucci.lasttraintolondon.lines.ui

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

//TODO move to onw module

fun SwipeRefreshLayout.refreshes(): Flow<Unit> = callbackFlow {
    setOnRefreshListener {
        offer(Unit)
    }
    awaitClose {
        setOnRefreshListener(null)
    }
}