import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

hilt{
    enableAggregatingTask = false
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

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.mvi.mviGeneral)
            implementation(projects.common.mvi.mviDi)
            implementation(projects.features.mainScreen.mainScreenApi)
            implementation(projects.features.mainScreen.mainScreenImpl)
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.dagger.hilt.android)
            implementation(libs.dagger.hilt.navigation.compose)
            implementation(libs.javapoet)
        }
    }
}

dependencies{
    add("kspAndroid", libs.dagger.hilt.compiler)
}

android {
    namespace = "ru.braveowlet.kmmpr.features.first_screen.di"
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
