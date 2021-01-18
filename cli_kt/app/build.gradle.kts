plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    application
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Discord
    implementation("net.dv8tion:JDA:4.2.0_227")

    // Redis
    implementation("io.lettuce:lettuce-core:6.0.2.RELEASE")

}

application {
    mainClass.set("net.nanai10a.twomeat.cli.MainKt")
}
