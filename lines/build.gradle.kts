apply<coverage.CoveragePlugin>()
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    kotlin("plugin.serialization") // no version specified cause it's being added in the classpath
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

    buildFeatures {
        viewBinding =  true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

configure<coverage.CoveragePlugin.CoverageExtension> {
    excludes = mutableListOf(
        "**/di/**",
        "**/*Screen*",
        "**/*ViewHolder*",
        "**/*Decorator*",
        "**/*Adapter",
        "**/*_Factory*",
        "**/*RestLineMode*",
        "**/BuildConfig*"
    )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}


dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":core"))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinxSerialization)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.swipeToRefresh)
    implementation(Libs.AndroidX.material)
    implementation(TestLibs.Esspresso.idlingResources)
    implementation(Libs.Arrow.core)
    implementation(Libs.Arrow.syntax)

    kapt(Libs.Dagger.compiler)
    kapt(Libs.Arrow.compiler)


    testImplementation(TestLibs.mockitoKotlin)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.truth)
    testImplementation(project(":ristretto"))

    //FOr live data in view model test
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    kaptAndroidTest(Libs.Dagger.compiler)
    androidTestImplementation(Libs.Dagger.core)
    androidTestImplementation(TestLibs.mockitoKotlin)
    androidTestImplementation(TestLibs.mockWebServer)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.uiAutomator)
    androidTestImplementation(TestLibs.Esspresso.jUnit)
    androidTestImplementation(TestLibs.Esspresso.core)
    androidTestImplementation(TestLibs.fragmentTesting)
    androidTestImplementation(TestLibs.Esspresso.idlingResources)
    androidTestImplementation(TestLibs.Esspresso.contrib)
    androidTestImplementation(Libs.okHttp)

    androidTestImplementation(project(":ristretto"))
    androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")
}