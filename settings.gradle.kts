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
include(":common:mvi:mvi-general")
include(":common:mvi:mvi-di")
include(":common:logger")
include(":common:platform")

include(":features:main-screen:main-screen-api")
include(":features:main-screen:main-screen-impl")
include(":features:main-screen:main-screen-di")