plugins {
    id("com.android.library")
    id("kotlin-android")
}
android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.Retrofit.retrofit)

    debugImplementation(Libs.Stetho.stetho)
    debugImplementation(Libs.Stetho.stethoOk)
    testImplementation(TestLibs.jUnit)
}
