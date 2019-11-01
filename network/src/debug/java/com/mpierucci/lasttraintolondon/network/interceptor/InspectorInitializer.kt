package com.mpierucci.lasttraintolondon.network.interceptor

import android.app.Application
import com.facebook.stetho.Stetho
import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import javax.inject.Inject

class InspectorInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        Stetho.initializeWithDefaults(application)
    }
}