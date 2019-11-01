package com.mpierucci.lasttraintolondon.init

import android.app.Application
import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {

    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}