plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.50"
}

repositories {
    jcenter()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "application")

    repositories {
        jcenter()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
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

        for (candidate in File("${project.projectDir}/problem-XXXX").listFiles() ?: emptyList()) {
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
