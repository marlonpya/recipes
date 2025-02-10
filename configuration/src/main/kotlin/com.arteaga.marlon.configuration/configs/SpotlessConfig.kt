
package com.arteaga.marlon.configuration.configs

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

object SpotlessConfig {
    fun apply(target: Project) {

        target.configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                ktlint()
                licenseHeader("/** Marlon Arteaga **/")
            }
            kotlinGradle {
                target("**/*.gradle.kts")
                ktlint()
            }
        }
    }
}
