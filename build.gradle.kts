plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.mokoResourcesPlugin) apply false
}

buildscript{
    dependencies{
        classpath(libs.plugins.mokoResourcesClasspath.get().toString())
    }
}

task("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}