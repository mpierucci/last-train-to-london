plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)
    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName =  Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug"){
            applicationIdSuffix = ".debug"

        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

}

dependencies {
    implementation(project(":network"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.Ktx.core)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.material)
    implementation(Libs.Dagger.core)

    kapt(Libs.Dagger.compiler)

    debugImplementation(Libs.leakCanary)

    testImplementation(TestLibs.jUnit)

    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.Esspresso.core)
}
