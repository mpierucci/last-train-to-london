package com.mpierucci.lasttraintolondon

import android.app.Application
import com.mpierucci.lasttraintolondon.di.DaggerAppComponent

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .factory()
            .create(this)
    }
}