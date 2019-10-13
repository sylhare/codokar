import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "com.github.sylhare.codokar"
    version = "1.0"
    repositories {
        jcenter()
    }
}

plugins {
    application
    kotlin("jvm") version "1.3.21"
    jacoco
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.21")
    testCompile("junit:junit:4.12")
}

// To work with the `Application plugin of gradle` -> gradle run
application {
    mainClassName = "com.github.sylhare.codokar.cli.MakeOKRKt"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.github.sylhare.codokar.cli.MakeOKRKt"
    }
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "com.github.sylhare.codokar.cli.MakeOKRKt"
    }
    from(
        configurations.runtime.map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    with(tasks["jar"] as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/reports/jacoco")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}