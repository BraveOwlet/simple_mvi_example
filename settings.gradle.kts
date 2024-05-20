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
include(":common:mvi:mvi-koin-voyager")
include(":common:logger")
include(":common:platform")

// CORE
include(":core:network")
include(":core:database")
include(":core:recources")

// FEATURES
include(":features:main-screen:main-screen-api")
include(":features:main-screen:main-screen-impl")
include(":features:dog-screens:dog-screens-api")
include(":features:dog-screens:dog-screens-impl")

// COMPONENTS
include(":components:dogs:dogs")