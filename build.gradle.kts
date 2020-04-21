import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import properties.gitHubProperties

plugins {
    kotlin("jvm")
}
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinSerialization)
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.1.1")
        classpath(BuildPlugins.googlePlay)
        classpath ("org.jacoco:org.jacoco.core:0.8.5")
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
            credentials {
                username =
                    System.getenv("GPR_USER") ?: project.gitHubProperties.getProperty("gpr.usr")
                password =
                    System.getenv("GPR_API_KEY") ?: project.gitHubProperties.getProperty("gpr.key")
            }
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
