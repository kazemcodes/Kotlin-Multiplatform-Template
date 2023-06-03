plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.compose.desktop.plugin)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.company.kmp_template.android"
        minSdk = 21
        targetSdk = compileSdk
        versionCode = 1
        versionName = "1.0.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility(ProjectConfig.androidJvmTarget)
        targetCompatibility(ProjectConfig.androidJvmTarget)
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.androidJvmTarget.toString()
    }
}

dependencies {
    api(project(":shared"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidX.core)

    implementation(compose.material3)

    implementation(compose.ui)
    implementation(compose.uiTooling)
    implementation(compose.runtime)
    implementation(libs.compose.util)
    implementation(libs.compose.activity)

    implementation(libs.lifecycle.runtime)

    // Koin-Dependency injection
    implementation(libs.koin.android)
    implementation(libs.koin.compose.android)

    // Compose Navigation-Navigation between various screens
    implementation(libs.navigation.compose)

    testImplementation(libs.jUnitKtx)
    testImplementation(libs.kotlinX.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.archTestCore)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.runner)
}
