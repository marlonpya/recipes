
package com.arteaga.marlon.configuration.shared

import org.gradle.api.Project

object Functions {
    fun applyCommonPlugins(project: Project) {
        project.plugins.apply("org.jlleitschuh.gradle.ktlint")
        project.plugins.apply("io.gitlab.arturbosch.detekt")
        project.plugins.apply("com.diffplug.spotless")
    }
}
