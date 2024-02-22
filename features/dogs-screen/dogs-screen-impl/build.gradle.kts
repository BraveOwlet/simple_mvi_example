import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.mokoResourcesPlugin)
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

    multiplatformResources {
        resourcesPackage = "ru.braveowlet.features.dogs_screen.impl"
        resourcesVisibility = MRVisibility.Public
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.logger)
            implementation(projects.common.mvi.mviGeneral)
            implementation(projects.common.mvi.mviKoin)
            implementation(projects.core.recources)
            implementation(projects.core.network)
            implementation(projects.components.dogs)
            implementation(projects.features.dogsScreen.dogsScreenApi)

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)

            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)

            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network)
        }
    }
}

android {
    namespace = "ru.braveowlet.kmmpr.features.dogs_screen.impl"
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
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
