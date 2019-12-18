package com.mpierucci.lasttraintolondon.core.init

import android.app.Application
import com.mpierucci.lasttraintolondon.core.init.AppInitializer
import javax.inject.Inject

class InspectorInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) = Unit // no op
}