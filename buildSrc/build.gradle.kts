repositories {
    google()
    jcenter()
}
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.1.1")
    implementation(kotlin("gradle-plugin", "1.4.10"))

    implementation("org.jacoco:org.jacoco.core:0.8.5")
}