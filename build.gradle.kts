import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "com.github.sylhare.codokar"
    version = "1.2"
    repositories {
        jcenter()
    }
}

plugins {
    application
    kotlin("jvm") version "1.3.21"
    jacoco
    `maven-publish`
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("junit:junit:4.12")
}

// To work with the `Application plugin of gradle` -> gradle run
application {
    mainClassName = "com.github.sylhare.codokar.cli.MakeOKRKt"
}


// Super charge the jar to work like a fatJar
// https://stackoverflow.com/a/61373175/7747942
//val jar by tasks.getting(Jar::class) {
//    manifest {
//        attributes["Main-Class"] = "com.github.sylhare.codokar.cli.MakeOKRKt"
//    }
//
//    from(sourceSets.main.get().output)
//    dependsOn(configurations.runtimeClasspath)
//    from({
//        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
//    })
//}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "com.github.sylhare.codokar.cli.MakeOKRKt"
    }

    from(
        configurations["runtimeClasspath"].map {
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sylhare/codokar")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_API_KEY")
            }
        }
    }
    publications {
        create<MavenPublication>("codokar") {
            from(components["java"])
        }
    }
}