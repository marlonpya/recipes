
package com.arteaga.marlon.configuration.plugins

import com.android.build.gradle.BaseExtension
import com.arteaga.marlon.configuration.constants.AndroidBuildConfig
import com.arteaga.marlon.configuration.constants.BuildTypeEnum
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ConfigLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            setupGeneral()
            setupAndroid()
            setupKapt()
        }
    }

    private fun Project.setupGeneral() {

        plugins.apply("com.android.library")
        plugins.apply("org.jetbrains.kotlin.android")
        plugins.apply("com.google.dagger.hilt.android")
        plugins.apply("plugins.jacoco")

        tasks {
            withType(KotlinCompile::class.java) {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
                    freeCompilerArgs = freeCompilerArgs + AndroidBuildConfig.requiresOptIn
                }
            }
        }
    }

    private fun Project.setupKapt() {
        plugins.apply("kotlin-kapt")
        val kaptExt = extensions["kapt"]

        if (kaptExt is KaptExtension) {
            with(kaptExt) {
                correctErrorTypes = true
            }
        }
    }

    private fun Project.setupAndroid() {
        val androidExt = extensions["android"]
        if (androidExt is BaseExtension) {
            with(androidExt) {
                compileSdkVersion(AndroidBuildConfig.compileSdk)

                buildTypes.getByName(BuildTypeEnum.Release.value) {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                defaultConfig {

                    minSdk = AndroidBuildConfig.minSdk
                    testInstrumentationRunner = AndroidBuildConfig.testInstrumentationRunner
                    consumerProguardFiles("consumer-rules.pro")
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                packagingOptions {
                    resources {
                        excludes += "/META-INF/**"
                    }
                }

                buildFeatures.buildConfig = true
            }
        }
    }
}
