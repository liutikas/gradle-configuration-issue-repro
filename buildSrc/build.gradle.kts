plugins {
    kotlin("jvm") version "1.4.32"
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation("com.github.jengelman.gradle.plugins:shadow:6.1.0")
}
