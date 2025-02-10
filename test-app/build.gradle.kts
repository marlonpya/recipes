@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.test")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.arteaga.marlon.test.app"
    compileSdk = 35
    targetProjectPath = ":app"

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "com.arteaga.marlon.domain.HiltTestRunner"
    }

    buildFeatures {
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.bundles.retrofit2)

    // Testing
    implementation(libs.androidx.test.core)

    // Hilt and instrumented tests.
    implementation(libs.hilt.android.testing)
    kapt(libs.google.dagger.compiler)

    // Compose
    implementation(libs.compose.ui.test.junit4)
}
