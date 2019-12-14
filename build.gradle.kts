import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

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
    val githubProperties by lazy {
        Properties().apply {
            load(FileInputStream(rootProject.file("github.properties")))
        }
    }

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mpierucci/Android-Architecture")
            credentials {
                username = System.getenv("GPR_USER") ?: githubProperties.getProperty("gpr.usr")
                password = System.getenv("GPR_API_KEY") ?: githubProperties.getProperty("gpr.key")
            }
        }
    }
    apply(file("$rootDir/qa/detekt/detekt.gradle"))
    apply(file("$rootDir/qa/ktlint/ktlint.gradle.kts"))
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