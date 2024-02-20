rootProject.name = "kmmpr"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

// APP
include(":app")

// CORE
include(":core:network")
include(":core:recources")

// COMMON
include(":common:mvi:mvi-general")
include(":common:mvi:mvi-koin")
include(":common:logger")
include(":common:platform")

// FEATURES
include(":features:main-screen:main-screen-api")
include(":features:main-screen:main-screen-impl")
include(":features:dogs-screen:dogs-screen-api")
include(":features:dogs-screen:dogs-screen-impl")
include(":features:saved-dogs-screen:saved-dogs-screen-api")
include(":features:saved-dogs-screen:saved-dogs-screen-impl")

// COMPONENTS
include(":components:dogs:dogs")