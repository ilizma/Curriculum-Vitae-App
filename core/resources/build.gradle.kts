import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.LONG
import java.util.regex.Pattern
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose)
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
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.core.ktx)
            implementation(libs.splashscreen)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.ilizma.resources"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    flavorDimensions.add("variant")

    productFlavors {
        create("lrhr") {
            dimension = "variant"
            isDefault = true
        }

        create("ilizma") {
            dimension = "variant"
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
    packageName = "com.ilizma.resources"

    defaultConfigs {
        buildConfigField(LONG, "MAIN_COLOR", "0xFFC8FF19")
    }

    defaultConfigs("lrhr") {
        buildConfigField(LONG, "MAIN_COLOR", "0xFFC8FF19")
    }
    defaultConfigs("ilizma") {
        buildConfigField(LONG, "MAIN_COLOR", "0xFFD9E3F2")
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.ilizma.resources"
    generateResClass = always
}
