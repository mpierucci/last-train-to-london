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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    viewBinding.isEnabled = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}


dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":core"))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(TestLibs.Esspresso.idlingResources)
    implementation(Libs.store)

    kapt(Libs.Dagger.compiler)


    testImplementation(TestLibs.mockitoKotlin)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.jUnit)
    testImplementation(project(":ristretto"))

    kaptAndroidTest(Libs.Dagger.compiler)
    androidTestImplementation(Libs.Dagger.core)
    androidTestImplementation(TestLibs.mockitoKotlin)
    androidTestImplementation(TestLibs.mockWebServer)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.uiAutomator)
    androidTestImplementation(TestLibs.Esspresso.jUnit)
    androidTestImplementation(TestLibs.Esspresso.core)
    androidTestImplementation(TestLibs.fragmentTesting)
    androidTestImplementation(TestLibs.Esspresso.idlingResources)
    androidTestImplementation(TestLibs.Esspresso.contrib)
    androidTestImplementation(project(":ristretto"))
}