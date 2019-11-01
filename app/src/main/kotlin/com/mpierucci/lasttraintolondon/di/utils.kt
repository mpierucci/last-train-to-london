package com.mpierucci.lasttraintolondon.di

import android.app.Activity

//TODO extract to common  module once start writing feature modules

val Activity.injector
    get() = (application as AppComponent.ComponentProvider).component