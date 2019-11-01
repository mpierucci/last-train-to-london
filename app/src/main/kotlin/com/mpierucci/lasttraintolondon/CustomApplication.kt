package com.mpierucci.lasttraintolondon

import android.app.Application
import com.mpierucci.lasttraintolondon.di.AppComponent
import com.mpierucci.lasttraintolondon.di.DaggerAppComponent

class CustomApplication : Application(), AppComponent.ComponentProvider {

    override val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}