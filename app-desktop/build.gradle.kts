plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.compose.desktop.plugin)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
    implementation(libs.koin.compose.core)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.turbine)
    testImplementation(libs.ktor.mock)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinX.coroutines.test)
}

compose.desktop {
    application {
        mainClass = "DesktopApplicationKt"
    }
}
