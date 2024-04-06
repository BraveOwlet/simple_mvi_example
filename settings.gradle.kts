rootProject.name = "kmmpr"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://androidx.dev/storage/compose-compiler/repository/")
    }
}

// APP
include(":app")

// COMMON
include(":common:mvi:mvi-general")
include(":common:mvi:mvi-koin")
include(":common:logger")
include(":common:platform")
include(":common:ui:design-system")

// CORE
include(":core:network")
include(":core:database")
include(":core:recources")

// FEATURES
include(":features:main-screen:main-screen-api")
include(":features:main-screen:main-screen-impl")
include(":features:dogs-screen:dogs-screen-api")
include(":features:dogs-screen:dogs-screen-impl")
include(":features:saved-dogs-screen:saved-dogs-screen-api")
include(":features:saved-dogs-screen:saved-dogs-screen-impl")
include(":features:resources-screen:resources-screen-api")
include(":features:resources-screen:resources-screen-impl")

// COMPONENTS
include(":components:dogs:dogs")