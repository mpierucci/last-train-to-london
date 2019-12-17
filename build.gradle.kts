import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Libs.kotlinVersion
}
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.kotlin)
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.1.1")
        classpath(BuildPlugins.googlePlay)
    }
}


allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mpierucci/Android-Architecture")
        }
    }
    apply(file("$rootDir/qa/detekt/detekt.gradle"))
    apply(file("$rootDir/qa/ktlint/ktlint.gradle.kts"))
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
