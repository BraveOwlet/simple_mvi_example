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

include(":app")

include(":core:network")

include(":common:mvi:mvi-general")
include(":common:mvi:mvi-compose")
include(":common:logger")
include(":common:platform")

include(":features:main-screen:main-screen")

include(":components:dogs:dogs-api")
include(":components:dogs:dogs-impl")
include(":components:dogs:dogs-di")