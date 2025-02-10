
package com.arteaga.marlon.configuration.plugins

import com.android.build.gradle.BaseExtension
import com.arteaga.marlon.configuration.constants.AndroidBuildConfig
import com.arteaga.marlon.configuration.constants.BuildTypeEnum
import com.arteaga.marlon.configuration.constants.Variables.addLibraryVariables
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

class ConfigEndpointsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            setupEndpoints()
        }
    }
    private fun Project.setupEndpoints() {
        val androidExt = extensions["android"]
        if (androidExt is BaseExtension) {
            with(androidExt) {
                compileSdkVersion(AndroidBuildConfig.compileSdk)

                buildTypes.getByName(BuildTypeEnum.Debug.value) {
                    addLibraryVariables(BuildTypeEnum.Debug)
                }

                buildTypes.getByName(BuildTypeEnum.Release.value) {
                    addLibraryVariables(BuildTypeEnum.Release)
                }
            }
        }
    }
}
