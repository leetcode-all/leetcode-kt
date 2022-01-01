plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }
}

open class LeetcodeAttempt : DefaultTask() {
    @set:Option(option = "number", description = "The number of the leetcode problem, XXXX")
    @get:Input
    var number: String = ""

    @TaskAction
    fun foo() {
        val numberPattern = "[0-9]{4}".toRegex()

        if (!numberPattern.matches(number)) {
            println("Please provide a valid problem number, was $number")

            return
        }

        if (!File("${project.projectDir}/problem-$number").mkdir()) {
            println("Directory problem-$number already exists")

            return
        }

        for (candidate in File("${project.projectDir}/problem-XXXX").listFiles()!!) {
            if (candidate.isDirectory && candidate.name.matches("build".toRegex())) {
                continue // will skip build directory if it exists
            }

            candidate.copyRecursively(File("${project.projectDir}/problem-$number/${candidate.name}"))
        }

        File("${project.projectDir}/problem-$number").walk()
            .forEach {
                if (it.isDirectory) {
                    return@forEach
                }

                it.writeText(it.readText().replace("problemXXXX", "problem$number"))
            }
    }
}

tasks.register<LeetcodeAttempt>("attempt")
