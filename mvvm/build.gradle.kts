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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.Rx.java)
    implementation(Libs.AndroidX.LifeCycle.lifeCycle)
}
