import properties.loadLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

apply<coverage.CoveragePlugin>()

android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    val localProperties by lazy { loadLocalProperties("$rootDir") }

    defaultConfig {
        buildConfigField(
            "String", "TFL_APP_ID",
            System.getenv("TFL_APP_ID") ?: "${localProperties["tfl.appId"]}"
        )
        buildConfigField(
            "String", "TFL_APP_KEY",
            System.getenv("TFL_APP_KEY") ?: "${localProperties["tfl.appKey"]}"
        )
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["debug"].java.srcDir("src/debug/kotlin")
    sourceSets["release"].java.srcDir("src/release/kotlin")
}

configure<coverage.CoveragePlugin.CoverageExtension> {
    additionalSourceDirs = listOf("src/debug/kotlin", "src/release/kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.Ktx.preferences)
    implementation(Libs.Hilt.core)

    api(Libs.Dagger.core)

    kapt(Libs.Dagger.compiler)
    kapt(Libs.Hilt.compiler)

    debugImplementation(Libs.Stetho.stetho)
    debugImplementation(Libs.Stetho.stethoOk)

    testImplementation(TestLibs.jUnit)
}

