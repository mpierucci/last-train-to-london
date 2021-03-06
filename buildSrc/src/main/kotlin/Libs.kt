object Libs {
    const val kotlinVersion = "1.4.10"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val ktlint = "com.github.shyiko:ktlint:0.36.0"

    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"


    const val timber = "com.jakewharton.timber:timber:4.7.1"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val material = "com.google.android.material:material:1.1.0"
        const val swipeToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

        object Ktx {
            const val core = "androidx.core:core-ktx:1.5.0-alpha05"
            const val fragment = "androidx.fragment:fragment-ktx:$fragmentXVersion"
            const val activity = "androidx.activity:activity-ktx:1.1.0"
            const val preferences = "androidx.preference:preference-ktx:1.1.1"
            const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"

            object Navigation {
                private const val version = "2.3.1"
                const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
                const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            }
        }

        object LifeCycle {
            private const val lifeCycleVersion = "2.2.0"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion"
            const val test = "androidx.arch.core:core-testing:$lifeCycleVersion"
        }
    }

    object Dagger {
        private const val daggerVersion = "2.27"
        const val core = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
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
        private const val retrofitVersion = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    }

    object Coroutines {
        private const val version = "1.3.7"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object FireBase {
        const val analytics = "com.google.firebase:firebase-analytics:17.2.1"
    }

    object Jake {
        const val kotlinxConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Arrow {
        private const val arrowVersion = "0.10.5"
        const val core = "io.arrow-kt:arrow-core:$arrowVersion"
        const val syntax = "io.arrow-kt:arrow-syntax:$arrowVersion"
        const val compiler = "io.arrow-kt:arrow-meta:$arrowVersion"
    }
}