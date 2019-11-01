package com.mpierucci.lasttraintolondon.core.init

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}