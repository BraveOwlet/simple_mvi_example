plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.moko.resources.plugin) apply false
}

buildscript{
    dependencies{
        classpath(libs.plugins.moko.resources.path.get().toString())
    }
}

task("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}