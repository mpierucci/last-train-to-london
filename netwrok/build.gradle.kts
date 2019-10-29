import properties.loadLocalProperties
import kotlin.lazy

plugins {
    id("com.android.library")
    id("kotlin-android")
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
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.rxAdapter)
    implementation(Libs.Retrofit.converter)
    implementation(Libs.Dagger.core)

    debugImplementation(Libs.Stetho.stetho)
    debugImplementation(Libs.Stetho.stethoOk)
    testImplementation(TestLibs.jUnit)
}


