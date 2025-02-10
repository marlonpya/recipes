
package com.arteaga.marlon.configuration.configs

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

object KtLintConfig {
    fun apply(target: Project) {
        target.configure<KtlintExtension> {
            version.set("0.42.1")
            debug.set(true)
            verbose.set(true)
            android.set(false)
            outputToConsole.set(true)
            outputColorName.set("RED")
            ignoreFailures.set(true)
            enableExperimentalRules.set(true)
            filter {
                exclude("**/generated/**")
                include("**/java/**")
            }
            reporters {
                reporter(ReporterType.PLAIN)
                reporter(ReporterType.CHECKSTYLE)
                reporter(ReporterType.HTML)
                reporter(ReporterType.JSON)
            }
        }
    }
}
