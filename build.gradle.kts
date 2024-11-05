import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin ("jvm") version "1.9.23"
    java
    application
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "3.0.1"
}

val junitVersion = ("5.10.2")
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

compileKotlin.destinationDirectory = compileJava.destinationDirectory

repositories {
    mavenCentral()
}

application {
    mainModule.set("org.mcralph.weightedaveragecalculatorkotlin")
    mainClass.set("org.mcralph.weightedaveragecalculatorkotlin.CalculatorApplication")
}

kotlin {
    jvmToolchain(21)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

javafx {
    modules = listOf("javafx.base", "javafx.controls")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

tasks.test {
    useJUnitPlatform()
}

jlink {
    launcher {
        name = "WavCal"
    }
}

//tasks.jar {
//    manifest {
//        attributes["Main-Class"] = application.mainClass
//    }
//    configurations["compileClasspath"].forEach { file: File ->
//        from(zipTree(file.absoluteFile))
//    }
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//}