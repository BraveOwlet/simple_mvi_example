import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.compose.ui.tooling.preview)

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            configurations["kspAndroid"].dependencies.addAll(
                listOf(
                    DefaultExternalModuleDependency(
                        libs.androidx.lifecycle.compiler.get().group,
                        libs.androidx.lifecycle.compiler.get().name,
                        libs.androidx.lifecycle.compiler.get().version?:""
                    ),
                    DefaultExternalModuleDependency(
                        libs.dagger.hilt.compiler.get().group,
                        libs.dagger.hilt.compiler.get().name,
                        libs.dagger.hilt.compiler.get().version?:""
                    ),
                )

            )
            implementation(libs.dagger.hilt.android)
            implementation(libs.dagger.hilt.navigation.compose)
        }
        commonMain.dependencies {
            implementation(project(":common:mvi"))
            implementation(libs.kotlinx.coroutines)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "ru.braveowlet.kmmpr.features.first_screen"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

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
