
package com.arteaga.marlon.configuration.configs

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

object DetektConfig {
    fun apply(target: Project) {
        target.configure<DetektExtension> {
            buildUponDefaultConfig = true
            allRules = false
            debug = true
            basePath = target.rootDir.path
            ignoreFailures = true
            config = target.files("${target.rootDir}/config/detekt/detekt.yml")
        }
        target.tasks.withType<Detekt>().configureEach {
            reports {
                html.required.set(true)
                xml.required.set(true)
                txt.required.set(true)
                sarif.required.set(true)
            }
        }
    }
}
