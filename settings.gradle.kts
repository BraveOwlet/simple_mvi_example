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

include(":composeApp")
include(":common:mvi")
include(":common:mvi-koin")
include(":common:logger")

include(":features:main-screen:api")
include(":features:main-screen:impl")
include(":features:main-screen:di")