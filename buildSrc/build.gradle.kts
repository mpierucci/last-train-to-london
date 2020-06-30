repositories {
    google()
    jcenter()
}
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.0.0")
    implementation(kotlin("gradle-plugin", "1.3.72"))

    implementation("org.jacoco:org.jacoco.core:0.8.5")
}