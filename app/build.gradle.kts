import dev.icerock.gradle.MRVisibility
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.moko.resources.plugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.java.version.int.get()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "app"
            binaryOptions["bundleId"] = "ru.braveowlet.kmmpr.app"
        }
    }

    multiplatformResources {
        resourcesPackage = "ru.braveowlet.kmmpr.app"
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
            implementation(projects.features.mainScreen.mainScreenApi)
            implementation(projects.features.mainScreen.mainScreenImpl)
            implementation(projects.features.dogsScreen.dogsScreenImpl)
            implementation(projects.features.savedDogsScreen.savedDogsScreenImpl)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.navigator)
            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.koin.android.navigation)
            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)
        }
    }
}

android {
    namespace = "ru.braveowlet.kmmpr.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "ru.braveowlet.kmmpr"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

