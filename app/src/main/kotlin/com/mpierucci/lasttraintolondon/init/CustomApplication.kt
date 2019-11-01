package com.mpierucci.lasttraintolondon.init

import android.app.Application
import com.mpierucci.lasttraintolondon.di.AppComponent
import com.mpierucci.lasttraintolondon.di.DaggerAppComponent
import javax.inject.Inject

class CustomApplication : Application(), AppComponent.ComponentProvider {

    @Inject
    lateinit var appInitializer: AppInitializers

    override val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        appInitializer.init(this)
    }
}