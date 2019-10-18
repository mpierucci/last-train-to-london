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
        classpath (BuildPlugins.androidGradle)
        classpath (BuildPlugins.kotlin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}