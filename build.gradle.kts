plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "info.skyblond"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // send email
    implementation("org.eclipse.angus:angus-mail:2.0.1")
    // do http post
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // http server
    implementation("io.javalin:javalin:5.4.2")
    // logger
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
