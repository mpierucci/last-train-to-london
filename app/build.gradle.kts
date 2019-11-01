import properties.loadLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)
    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val localProperties by lazy { loadLocalProperties("$rootDir") }

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("UPLOAD_KEY_ALIAS") ?: "${localProperties["uploadKey.alias"]}"
            keyPassword = System.getenv("UPLOAD_KEY_ALIAS_PASSWORD")
                ?: "${localProperties["uploadKey.aliasPassword"]}"
            storeFile = file(System.getenv("UPLOAD_KEY_PATH") ?: "lttlUploadKey")
            storePassword = System.getenv("UPLOAD_KEY_PASSWORD")
                ?: "${localProperties["uploadKey.password"]}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

}

dependencies {
    implementation(project(":network"))
    implementation(project(":viewmodel"))
    implementation(project(":core"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.Ktx.core)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.material)
    implementation(Libs.Dagger.core)
    implementation(Libs.gson)
    implementation(Libs.AndroidX.LifeCycle.lifeCycle)
    implementation(Libs.AndroidX.LifeCycle.viewModel)
    implementation(Libs.AndroidX.LifeCycle.liveData)

    kapt(Libs.Dagger.compiler)

    debugImplementation(Libs.leakCanary)

    testImplementation(TestLibs.mockitoKotlin)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.jUnit)

    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.Esspresso.core)
}
