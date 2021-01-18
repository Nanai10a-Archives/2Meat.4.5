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

    // slf4j implementation
    implementation("org.apache.logging.log4j:log4j-api:2.14.0")
    implementation("org.apache.logging.log4j:log4j-core:2.14.0")

    // Redis
    implementation("redis.clients:jedis:3.4.1")

    // support JSON
    implementation("com.google.code.gson:gson:2.8.6")

}

application {
    mainClass.set("net.nanai10a.twomeat.cli.MainKt")
}
