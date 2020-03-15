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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isAbortOnError =  true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    kapt(Libs.Dagger.compiler)
    implementation(project(":core"))

    implementation(Libs.Dagger.core)
    implementation(TestLibs.mockWebServer)
    implementation(TestLibs.testRunner)
    implementation(TestLibs.uiAutomator)
    implementation(TestLibs.Esspresso.jUnit)
    implementation(TestLibs.Esspresso.core)
    implementation(TestLibs.Esspresso.contrib)
    implementation(TestLibs.coroutinesTest)
    implementation(TestLibs.jUnit)
}


