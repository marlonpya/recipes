
package com.arteaga.marlon.configuration.configs

import com.android.build.gradle.BaseExtension
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.NodeChild
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import java.util.Locale
import kotlin.math.roundToInt

object JacocoConfig {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an Android module: $name")

    private val Project.jacoco: JacocoPluginExtension
        get() = extensions.findByName("jacoco") as? JacocoPluginExtension
            ?: error("Not a Jacoco module: $name")

    private val excludedFiles = mutableSetOf(
        "**/R.class",
        "**/R$*.class",
        "**/Manifest*.*",
        "android/**/*.*",
        "**/BuildConfig.*",
        "**/di",
        "**/*Hilt*.*",
        "**/*Hilt*/",
        "**/*hilt*.*",
        "**/*hilt*/",
        "**/*\$ViewBinder*.*",
        "**/*\$ViewInjector*.*",
        "**/Lambda$*.class",
        "**/Lambda.class",
        "**/*Lambda.class",
        "**/*Lambda*.class"
    )

    private val limits = mutableMapOf(
        "instruction" to 0.0,
        "branch" to 0.0,
        "line" to 0.0,
        "complexity" to 0.0,
        "method" to 0.0,
        "class" to 0.0
    )

    fun apply(target: Project) {
        with(target) {
            plugins.run {
                apply("jacoco")
            }

            jacoco.apply {
                toolVersion = "0.8.10"
            }

            android.buildTypes {
                getByName("debug") {
                    isTestCoverageEnabled = true
                }
            }

            tasks.withType(Test::class.java) {
                configure<JacocoTaskExtension> {
                    isIncludeNoLocationClasses = true
                    excludes = listOf("jdk.internal.*")
                }
            }

            extra.set("limits", limits)

            afterEvaluate {
                val buildTypes = android.buildTypes.map { type -> type.name }

                buildTypes.forEach { buildTypeName ->

                    tasks.withType<Test> {
                        ignoreFailures = true
                    }

                    val testTaskName = "test${
                    buildTypeName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString()
                    }
                    }UnitTest"

                    tasks.register<JacocoReport>("${testTaskName}Coverage") {
                        dependsOn(testTaskName, "createDebugCoverageReport")
                        group = "Reporting"
                        description =
                            "Generate Jacoco coverage reports on the ${
                            buildTypeName.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ENGLISH
                                ) else it.toString()
                            }
                            } build."

                        val javaDirectories = fileTree(
                            "${project.buildDir}\\intermediates\\javac\\debug"
                        ) { exclude(excludedFiles) }

                        val kotlinDirectories = fileTree(
                            "${project.buildDir}\\tmp\\kotlin-classes\\debug"
                        ) { exclude(excludedFiles) }

                        val kotlinDirectoriesExecutionData = fileTree(
                            project.buildDir
                        ) { include("**\\*.exec") }

                        println("${project.buildDir}")
                        println("${project.projectDir}")
                        println("${project.buildDir}\\jacoco\\reports")
                        println("${project.projectDir}\\jacoco\\reports")

                        val coverageSrcDirectories = listOf(
                            "${project.projectDir}\\src\\main\\java",
                            "${project.projectDir}\\src\\main\\kotlin",
                        )

                        classDirectories.setFrom(files(javaDirectories, kotlinDirectories))
                        additionalClassDirs.setFrom(files(coverageSrcDirectories))
                        sourceDirectories.setFrom(files(coverageSrcDirectories))
                        executionData.setFrom(kotlinDirectoriesExecutionData)

                        reports {
                            xml.required.set(true)
                            html.required.set(true)
                            html.outputLocation.set(file("${project.buildDir}\\reports\\jacoco"))
                        }

                        doLast {
                            jacocoTestReport("${testTaskName}Coverage")
                        }
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun Project.jacocoTestReport(testTaskName: String) {
        val reportsDirectory = jacoco.reportsDirectory.asFile.get()
        val report = file("$reportsDirectory/$testTaskName/$testTaskName.xml")

        logger.lifecycle("Checking coverage results: $report")

        val metrics = report.extractTestsCoveredByType()
        val limits = project.extra["limits"] as Map<String, Double>

        val failures = metrics.filter { entry ->
            entry.value < limits[entry.key]!!
        }.map { entry ->
            "- ${entry.key} coverage rate is: ${entry.value}%, minimum is ${limits[entry.key]}%"
        }

        if (failures.isNotEmpty()) {
            logger.quiet("------------------ Failed Coverage -----------------------")
            failures.forEach { logger.quiet(it) }
            logger.quiet("---------------------------------------------------------------")
            throw GradleException("Code coverage failed")
        }

        logger.quiet("------------------ Success Coverage -----------------------")
        metrics.forEach { entry ->
            logger.quiet("- ${entry.key} coverage rate is: ${entry.value}%")
        }
        logger.quiet("---------------------------------------------------------------")
    }

    @Suppress("UNCHECKED_CAST")
    private fun File.extractTestsCoveredByType(): Map<String, Double> {
        val xmlReader = XmlSlurper().apply {
            setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        }

        val counterNodes: List<NodeChild> = xmlReader
            .parse(this).parent()
            .children()
            .filter {
                (it as NodeChild).name() == "counter"
            } as List<NodeChild>

        return counterNodes.associate { nodeChild ->
            val type = nodeChild.attributes()["type"].toString().lowercase(Locale.ENGLISH)

            val covered = nodeChild.attributes()["covered"].toString().toDouble()
            val missed = nodeChild.attributes()["missed"].toString().toDouble()
            val percentage = ((covered / (covered + missed)) * 10000.0).roundToInt() / 100.0

            Pair(type, percentage)
        }
    }
}
