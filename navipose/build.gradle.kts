import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
    id("maven-publish")
}

group = "dev.likeAMonkeys.navipose"
version = "0.1-alpha"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "dev.likeAMonkeys"
                artifactId = "navipose"
                version = "0.1-alpha"
            }
        }
    }
}