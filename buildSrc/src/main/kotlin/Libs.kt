object Libs {
    const val kotlinVersion = "1.3.50"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val material = "com.google.android.material:material:1.1.0-beta01"

        object Ktx {
            const val core = "androidx.core:core-ktx:1.1.0"
        }
    }
}