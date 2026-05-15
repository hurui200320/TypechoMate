plugins {
    kotlin("jvm") version "2.3.20"
    application
}

group = "info.skyblond"
version = "1.1.2"

repositories {
    mavenCentral()
}

dependencies {
    // send email
    implementation("org.eclipse.angus:angus-mail:2.0.5")
    // do http post
    implementation("com.squareup.okhttp3:okhttp:5.3.2")
    // http server
    implementation("io.javalin:javalin:7.2.0")
    // logger
    implementation("ch.qos.logback:logback-classic:1.5.32")
    implementation("io.github.oshai:kotlin-logging-jvm:8.0.02")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("MainKt")
}
