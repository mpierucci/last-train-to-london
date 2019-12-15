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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.Dagger.core)
    kapt(Libs.Dagger.compiler)

    api("com.mpierucci.android.architecture:usecase:0.1.0")

    testImplementation(TestLibs.jUnit)
}
