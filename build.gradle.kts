repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

tasks.getByName("wrapper", Wrapper::class) {
    gradleVersion = "7.3.1"
}