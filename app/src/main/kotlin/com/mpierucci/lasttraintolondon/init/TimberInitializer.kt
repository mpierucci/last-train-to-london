package com.mpierucci.lasttraintolondon.init

import android.app.Application
import com.mpierucci.lasttraintolondon.BuildConfig
import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import timber.log.Timber
import javax.inject.Inject

// TODO https://github.com/mpierucci/last-train-to-london/issues/16
class TimberInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}