
package com.arteaga.marlon.configuration.plugins

import com.arteaga.marlon.configuration.configs.JacocoConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

class JacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.allprojects {
            JacocoConfig.apply(this)
        }
    }
}
