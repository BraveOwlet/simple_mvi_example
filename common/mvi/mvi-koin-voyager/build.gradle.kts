plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

compose.resources {
    generateResClass = never
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets{
        commonMain.dependencies {
            implementation(projects.common.logger)
            implementation(projects.common.utils)
            implementation(projects.common.mvi.mviGeneral)

            implementation(libs.kotlinx.coroutines.core)

            implementation(compose.runtime)

            implementation(libs.koin.core)

            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)
        }
        iosMain.dependencies{
            implementation(libs.touchlab.stately.common)
            implementation(libs.compose.runtime.saveable)
        }
    }
}

android {
    namespace = "ru.braveowlet.common.mvi.impl"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
