import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.activity.compose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.customactivityoncrash)

            // region Error Management
            implementation(project(":error-management-di"))
            // endregion
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.lifecycle.runtime.compose)
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.analytics)

            // View
            implementation(project(":view-base"))

            // Resources
            implementation(project(":resources"))

            // region Main
            implementation(project(":main-di"))
            // endregion

            // region Curriculum
            implementation(project(":curriculum-di"))
            // endregion

            // region Personal data
            implementation(project(":personal-di"))
            // endregion

            // region Education
            implementation(project(":education-di"))
            // endregion

            // region Work
            implementation(project(":work-di"))
            // endregion
        }

        androidInstrumentedTest.dependencies {
            implementation(libs.firebase.crashlytics.ktx)
            implementation(libs.google.firebase.analytics)
            implementation(libs.firebase.common.ktx)
        }
    }
}

android {
    namespace = "com.ilizma.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ilizma.curriculumvitaeapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("variant")

    productFlavors {
        create("lrhr") {
            dimension = "variant"
            isDefault = true
            applicationIdSuffix = ".lrhr"
            versionCode = ConfigData.lrhrVersionCode
            versionName = ConfigData.lrhrVersionName
        }

        create("ilizma") {
            dimension = "variant"
            applicationIdSuffix = ".ilizma"
            versionCode = ConfigData.ilizmaVersionCode
            versionName = ConfigData.ilizmaVersionName
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

project.extra.set("buildkonfig.flavor", currentBuildVariant())

private fun Project.currentBuildVariant(): String {
    val variants = setOf("lrhr", "ilizma")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: "lrhr"
}

private fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("lrhr", "ilizma")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1).lowercase() else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

buildkonfig {
    packageName = "com.ilizma.app"

    defaultConfigs {
        buildConfigField(STRING, "variant", "lrhr")
    }

    defaultConfigs("lrhr") {
        buildConfigField(STRING, "variant", "lrhr")
    }
    defaultConfigs("ilizma") {
        buildConfigField(STRING, "variant", "ilizma")
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
