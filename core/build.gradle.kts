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
    implementation(Libs.Dagger.core)

    testImplementation(TestLibs.jUnit)
}
