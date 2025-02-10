import com.arteaga.marlon.configuration.constants.AndroidBuildConfig

plugins {
    id("plugins.config.library")
    id("plugins.config.endpoints")
}

android {
    namespace = "${AndroidBuildConfig.basePackage}.data"
}

dependencies {
    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.library.implements)
    kapt(libs.bundles.kapt)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.library.androidTest)
    androidTestImplementation(libs.bundles.retrofit2)
    implementation(libs.converter.gson)

    kaptAndroidTest(libs.bundles.kapt)
    implementation(project(":domain"))
}
