import com.arteaga.marlon.configuration.constants.AndroidBuildConfig

plugins {
    id("plugins.config.app")
}

android {
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidBuildConfig.composeVersion
    }
    defaultConfig {
        applicationId = "com.arteaga.marlon.recipes"
    }
}

dependencies {

    val composeBom = platform(libs.compose.boom)
    implementation(composeBom)
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.bundles.app.implements)
    kapt(libs.bundles.kapt)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.bundles.app.androidTest)
    testImplementation(libs.bundles.test)
    debugImplementation(libs.bundles.app.debud.implements)
}
