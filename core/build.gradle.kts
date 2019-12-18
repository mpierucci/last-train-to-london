plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)
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
    api("com.mpierucci.android.architecture:usecase:0.1.0")
    api("com.mpierucci.android.architecture:viewmodel:0.1.1")
    api(Libs.AndroidX.LifeCycle.lifeCycle)
    api(Libs.AndroidX.LifeCycle.viewModel)
    api(Libs.AndroidX.LifeCycle.liveData)
    api(Libs.AndroidX.Ktx.fragment)
    api(Libs.AndroidX.material)
    api(Libs.AndroidX.Ktx.Navigaiton.fragment)
    api(Libs.AndroidX.Ktx.Navigaiton.ui)
    api(Libs.AndroidX.Ktx.core)
    api(Libs.AndroidX.appCompat)
    api(Libs.Coroutines.core)


    debugImplementation(Libs.Stetho.stetho)

    testImplementation(TestLibs.jUnit)
}
