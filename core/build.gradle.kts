plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["debug"].java.srcDir("src/debug/kotlin")
    sourceSets["release"].java.srcDir("src/release/kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    kapt(Libs.Dagger.compiler)


    //SHARED DEPENDENCIES
    api(project(":network"))
    api(project(":preferences"))
    api("com.mpierucci.android.architecture:viewmodel:0.1.3")
    api(Libs.AndroidX.LifeCycle.viewModel)
    api(Libs.AndroidX.LifeCycle.liveData)
    api(Libs.AndroidX.Ktx.fragment)
    api(Libs.AndroidX.material)
    api(Libs.AndroidX.Ktx.Navigation.fragment)
    api(Libs.AndroidX.Ktx.Navigation.ui)
    api(Libs.AndroidX.Ktx.core)
    api(Libs.AndroidX.appCompat)
    api(Libs.Coroutines.core)
    api(Libs.timber)


    api(TestLibs.coroutinesTest)

    debugImplementation(Libs.Stetho.stetho)

    testImplementation(TestLibs.jUnit)
}
