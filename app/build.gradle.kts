import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import properties.loadLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
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

    signingConfigs {
        create("release") {
            val localProperties by lazy { loadLocalProperties("$rootDir") }
            keyAlias = System.getenv("BITRISEIO_ANDROID_KEYSTORE_ALIAS") ?: "${localProperties["uploadKey.alias"]}"
            keyPassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD")
                ?: "${localProperties["uploadKey.aliasPassword"]}"
            storeFile = file(System.getenv("DOWNLOADED_KEYSTORE_PATH") ?: "lttlUploadKey")
            storePassword = System.getenv("BITRISEIO_ANDROID_KEYSTORE_PASSWORD")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}


dependencies {
    implementation(project(":network"))
    implementation(project(":viewmodel"))
    implementation(project(":usecase"))
    implementation(project(":core"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.Ktx.fragment)
    implementation(Libs.AndroidX.Ktx.core)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.material)
    implementation(Libs.Dagger.core)
    implementation(Libs.gson)
    implementation(Libs.AndroidX.LifeCycle.lifeCycle)
    implementation(Libs.AndroidX.LifeCycle.viewModel)
    implementation(Libs.AndroidX.LifeCycle.liveData)
    implementation(Libs.timber)
    implementation(Libs.FireBase.analytics)

    kapt(Libs.Dagger.compiler)

    debugImplementation(Libs.leakCanary)

    testImplementation(TestLibs.mockitoKotlin)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.jUnit)

    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.Esspresso.core)
}
