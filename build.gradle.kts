plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "xyz.koiro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    maven("https://raw.github.com/SpinyOwl/repo/releases")
    maven("https://jitpack.io")
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}


val lwjglNatives = "natives-macos-arm64"
val lwjglVersion = "3.3.2"
val leguiVersion = "4.1.0"
val kotlinLoggingJvmVersion = "5.1.0"
val mordantVersion = "2.1.0"

dependencies {
    testImplementation(kotlin("test"))

    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-assimp")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-yoga")
    implementation("org.lwjgl:lwjgl-zstd")
    implementation("org.lwjgl:lwjgl-stb")
    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-jemalloc")
    implementation("org.lwjgl", "lwjgl-remotery")
    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-assimp::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-yoga::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-zstd::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-stb::$lwjglNatives")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-jemalloc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-remotery", classifier = lwjglNatives)

    implementation("org.joml:joml:1.10.5")

    //imgui
    implementation("kotlin.graphics:imgui-core:1.89.7")
    implementation("kotlin.graphics:imgui-gl:1.89.7")
    implementation("kotlin.graphics:imgui-glfw:1.89.7")

    //gln
    implementation("com.github.kotlin-graphics:gln:v0.5.32")

    //log
    implementation("io.github.oshai:kotlin-logging-jvm:${kotlinLoggingJvmVersion}")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("com.github.ajalt.mordant:mordant:${mordantVersion}")
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

repositories {
    mavenCentral()
}
