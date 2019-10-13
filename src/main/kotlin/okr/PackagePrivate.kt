package okr

import java.io.File

private val String.sourcePath
    get() = File(this).walkTopDown()
        .filterNot { it.path.contains("test") }
        .filter { it.isFile }.toList()

val String.kotlinClasses get() = this.sourcePath.filter { it.path.endsWith(".kt") }
val String.javaClasses get() = this.sourcePath.filter { it.path.endsWith(".java") }
val String.allClasses get() = listOf(this.kotlinClasses, this.javaClasses).flatten()

val String.kotlinPublicClasses
    get() = this.kotlinClasses
        .filter { it.readText().contains("class".toRegex()) }
        .filterNot { it.readText().contains("internal(.*|\n)class".toRegex()) }
        .filterNot { it.readText().contains("private(.*|\n)class".toRegex()) }

val String.javaPublicClasses
    get() = this.javaClasses
        .filter { it.readText().contains("public(.*|\n)class".toRegex()) }

val String.allPublicClasses get() = listOf(this.kotlinPublicClasses, this.javaPublicClasses).flatten()

val String.percentagePrivateClasses
    get() = (100 * (1 - this.allPublicClasses.size.toFloat() / this.allClasses.size.toFloat())).roundTo(
        1
    )

fun Number.roundTo(numFractionDigits: Int) = String.format("%.${numFractionDigits}f", toDouble())