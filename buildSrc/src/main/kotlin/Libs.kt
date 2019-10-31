object Libs {
    const val kotlinVersion = "1.3.50"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"

    const val ktlint = "com.github.shyiko:ktlint:0.31.0"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val material = "com.google.android.material:material:1.1.0-beta01"

        object Ktx {
            const val core = "androidx.core:core-ktx:1.1.0"
            const val fragment = "androidx.fragment:fragment-ktx:1.1.0"
        }

        object LifeCycle {
            private const val lifeCycleVersion = "2.1.0"
            const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion"
            const val liveData = "androidx.lifecycle:lifecycle-livedata:$lifeCycleVersion"
            const val test = "androidx.arch.core:core-testing:2.0.1"
        }
    }

    object Dagger {
        private const val daggerVersion = "2.24"
        const val core = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val androidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"
        const val androidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    }

    object Rx {
        const val java = "io.reactivex.rxjava2:rxjava:2.2.13"
        const val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val bindings = "com.jakewharton.rxbinding3:rxbinding:3.0.0"
    }

    object Stetho {
        private const val stethoVersion = "1.5.1"
        const val stetho = "com.facebook.stetho:stetho:$stethoVersion"
        const val stethoOk = "com.facebook.stetho:stetho-okhttp3:$stethoVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.6.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val converter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    }

    object Coroutines {
        private const val version = "1.3.2"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }
}