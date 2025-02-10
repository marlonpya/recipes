
package com.arteaga.marlon.configuration.plugins

import com.arteaga.marlon.configuration.configs.DetektConfig
import com.arteaga.marlon.configuration.configs.KtLintConfig
import com.arteaga.marlon.configuration.configs.SpotlessConfig
import com.arteaga.marlon.configuration.shared.Functions
import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigurationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.allprojects {
            Functions.applyCommonPlugins(this)
            KtLintConfig.apply(this)
            DetektConfig.apply(this)
            SpotlessConfig.apply(this)
        }
    }
}
