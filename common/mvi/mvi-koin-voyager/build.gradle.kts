plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.java.version.int.get()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets{
        commonMain.dependencies {
            implementation(projects.common.logger)
            implementation(projects.common.platform)
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
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
