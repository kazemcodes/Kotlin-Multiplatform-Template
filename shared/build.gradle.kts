import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.sqlDelight.plugin)
    alias(libs.plugins.moko)
    alias(libs.plugins.compose.desktop.plugin)
    // alias(libs.plugins.nativeCocoapod)
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = compileSdk
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(ProjectConfig.androidJvmTarget)
        targetCompatibility(ProjectConfig.androidJvmTarget)
    }

}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = ProjectConfig.desktopJvmTarget.toString()
    }
}
kotlin {
    jvmToolchain(ProjectConfig.toolChain)
    android {
        compilations {
            all {
                kotlinOptions.jvmTarget = ProjectConfig.androidJvmTarget.toString()
            }
        }
    }
    jvm("desktop") {
        compilations {
            all {
                kotlinOptions.jvmTarget = ProjectConfig.desktopJvmTarget.toString()
            }
        }
    }


    val iosTarget: (kotlin.String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> kotlin.Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget =
        when {
            java.lang.System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            java.lang.System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget("iOS") {}
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.runtime)
                api(compose.material3)
                api(compose.animation)
                api(compose.animationGraphics)
                api(compose.foundation)
                api(compose.materialIconsExtended)

                api(libs.koin.core)
                api(libs.koin.compose.core)
                api(libs.ktor.core)
                api(libs.ktor.cio)
                implementation(libs.ktor.contentNegotiation)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)

                implementation(libs.kotlinX.serializationJson)

                implementation(libs.sqlDelight.runtime)
                implementation(libs.sqlDelight.coroutine)

                implementation(libs.multiplatformSettings.noArg)
                implementation(libs.multiplatformSettings.coroutines)
                implementation(libs.multiplatformSettings.serialization)
                implementation(libs.multiplatformSettings.core)

                api(libs.napier)

                implementation(libs.kotlinX.dateTime)


                implementation(libs.voyager.core)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tabNavigator)

                implementation(libs.moko.core)
                implementation(libs.moko.compose)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)
                api(libs.koin.android)
                api(libs.koin.compose.android)
            }
        }

        val iOSMain by getting {
            dependencies {
                implementation(libs.sqlDelight.native)
            }
        }
        val iOSTest by getting {
            dependencies {
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.sqlDelight.jvm)
            }
        }
        val desktopTest by getting {
            dependencies {
            }
        }
    }
}

sqldelight {
    database(name = "AppDatabase") {
        packageName = "com.vickikbt.kmptemplate.data.cache.sqldelight"
        sourceFolders = kotlin.collections.listOf("kotlin")
    }
}
