import properties.loadLocalProperties
import kotlin.lazy

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    val localProperties  by lazy { loadLocalProperties("$rootDir")}

    defaultConfig {
        buildConfigField("String","TFL_APP_ID",
            System.getenv("TFL_APP_ID")?:"${localProperties["tfl.appId"]}")
        buildConfigField("String","TFL_APP_KEY",
            System.getenv("TFL_APP_KEY")?:"${localProperties["tfl.appKey"]}")
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["debug"].java.srcDir("src/debug/kotlin")
    sourceSets["release"].java.srcDir("src/release/kotlin")
}

dependencies {
    api(Libs.okHttp)
    api(Libs.Retrofit.retrofit)


    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinxSerialization)
    implementation(Libs.Retrofit.rxAdapter)
    implementation(Libs.Jake.kotlinxConverter)
    api(Libs.Retrofit.gsonConverter)

    api(Libs.Dagger.core)

    kapt(Libs.Dagger.compiler)

    debugImplementation(Libs.Stetho.stetho)
    debugImplementation(Libs.Stetho.stethoOk)

    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.mockWebServer)
}


