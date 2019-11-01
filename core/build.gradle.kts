plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.Dagger.core)
    kapt(Libs.Dagger.compiler)

    testImplementation(TestLibs.jUnit)
}
