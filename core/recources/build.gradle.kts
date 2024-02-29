plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
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
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "ru.braveowlet.kmmpr.core.recources"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
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
}

// After executing the "generateComposeResClass" task, we replace the internal stuff by public.
tasks.named("generateComposeResClass") {
    doLast {
        val dirName = buildString {
            val group = project.group.toString()
                .lowercase()
                .replace('.', '/')
                .replace('-', '_')
            append(group)
            if (group.isNotEmpty()) {
                append("/")
            }
            append(project.name.lowercase())
        }
        val dir = project.layout.buildDirectory
            .dir("generated/compose/resourceGenerator/kotlin/$dirName/generated/resources")
            .get()
            .asFile
        File(dir, "Res.kt").also {
            if (!it.exists()) {
                return@also
            }
            val content = it.readText()
            val updatedContent = content.replace("internal object Res {", "object Res {")
            it.writeText(updatedContent)
        }
        listOf("Drawable0.kt", "String0.kt").forEach { filename ->
            File(dir, filename).also { file ->
                if (!file.exists()) {
                    return@also
                }
                val content = file.readText()
                val updatedContent = content.replace("internal val Res", "val Res")
                file.writeText(updatedContent)
            }
        }
    }
}