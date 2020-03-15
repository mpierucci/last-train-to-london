plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(Libs.kotlinStdlib)
    implementation(Libs.Coroutines.core)

    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.jUnit)

}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
